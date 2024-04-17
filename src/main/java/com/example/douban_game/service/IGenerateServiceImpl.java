package com.example.douban_game.service;


import com.example.douban_game.dao.GenerateMapper;
import com.example.douban_game.entity.Area;
import com.example.douban_game.entity.GenerateData;
import com.example.douban_game.entity.Language;

import com.example.douban_game.fakeData.test1;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IGenerateServiceImpl implements IGenerateService {

    @Autowired
    private GenerateMapper generateMapper;

//   @Autowired
//    IGenerateService iGenerateService;

    @Override
    public int countHistory() {
        return generateMapper.countHistory();
    }

    @Override
    public List<GenerateData> getGenerateHistory(int operator_id) {
        return generateMapper.getGenerateHistory(operator_id);
    }

    @Override
    public void GenerateData(GenerateData gd) {
        generateMapper.GenerateData(gd);
    }

    @Override
    public void GenerateAreaData(Area area) {
        generateMapper.GenerateAreaData(area);
    }

    @Override
    public void GenerateLanguageData(Language language) {
        generateMapper.GenerateLanguageData(language);
    }

    @Override
    public List<GenerateData> selectHistoryByOperatorIp(int operator_id) {
        return generateMapper.selectHistoryByOperatorIp(operator_id);
    }

    @Override
    public List<GenerateData> selectHistoryByID(int id) {
        return generateMapper.selectHistoryByID(id);
    }

    @Override
    public List<Integer> selectAllFakeId() {
        return generateMapper.selectAllFakeId();
    }

    @Override
    public List<GenerateData> findAllDataByPageF(int operator_id, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true, false, false);
        List<GenerateData> lists = generateMapper.getGenerateHistory(operator_id);
        return lists;
    }

    @Override
    public PageInfo<GenerateData> findAllDataByPageS(int operator_id, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true, false, false);
        List<GenerateData> lists = generateMapper.getGenerateHistory(operator_id);
        PageInfo<GenerateData> pageInfo = new PageInfo<>(lists);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class,isolation = Isolation.READ_UNCOMMITTED)
    public void generateTestData(int op_id, int f_id) throws Exception {
        test1 t = new test1();

//        String path = "D:\\专综2\\data\\input\\kind.csv";
//        String path2 = "D:\\专综2\\data\\input\\location.csv";
//        String path3 = "D:\\专综2\\data\\input\\language.csv";

        String path = "/home/basedata/kind.csv";
        String path2 = "/home/basedata/location.csv";
        String path3 = "/home/basedata/language.csv";

        String[] kind = new String[]{"action", "rolePlay", "adventure", "alpinia"};
        String[] location = new String[]{"north_us", "south_us", "asia", "europe"};
        String[] language = new String[]{"chinese", "japanese", "english", "other_language"};
        String[] platforms = new String[]{"PC", "ps4", "Mac", "Android","XBOX"};


        //写入文件header
        writeHeader(path, "kind");
        writeHeader(path2, "location");
        writeHeader(path3, "language");


        String[] options = new String[]{"kind", "location", "language"};
        //String[] options = new String[]{"kind"};

        for (String op : options) {

            //int total = generateMapper.getGenerateHistory(op_id).get(f_id).getGenerate_number();
            int total = 0;
            GenerateData gd = new GenerateData();
            List<GenerateData> g1 = generateMapper.getGenerateHistory(op_id);
            for (int i = 0; i <g1.size() ; i++) {
                if(g1.get(i).getFake_id()==f_id){
                    total =g1.get(i).getGenerate_number();
                    gd = g1.get(i);
                    break;
                }
            }

            System.out.println("------");

            if (op.equals("kind")) {
                t.WriteConListtent(op, path,
                        test1.AreaDataTempCreate(total / 100 * gd.getAction(), op, kind[0]));

                t.WriteConListtent(op, path,
                        test1.AreaDataTempCreate(total / 100 * gd.getRoleplay(), op, kind[1]));

                t.WriteConListtent(op, path,
                        test1.AreaDataTempCreate(total / 100 * gd.getAdventure(), op, kind[2]));

                t.WriteConListtent(op, path,
                        test1.AreaDataTempCreate(total / 100 * gd.getAlpinia(), op, kind[3]));
            } else if (op.equals("location")) {
                t.WriteConListtent(op, path2,
                        test1.AreaDataTempCreate(total / 100 * gd.getArea().getNorth_us(), op, location[0]));

                t.WriteConListtent(op, path2,
                        test1.AreaDataTempCreate(total / 100 * gd.getArea().getSouth_us(), op, location[1]));

                t.WriteConListtent(op, path2,
                        test1.AreaDataTempCreate(total / 100 * gd.getArea().getAsia(), op, location[2]));

                t.WriteConListtent(op, path2,
                        test1.AreaDataTempCreate(total / 100 * gd.getArea().getEurope(), op, location[3]));
            } else {
                t.WriteConListtent(op, path3,
                        test1.LanguageDataTempCreate(total / 100 * gd.getLanguage().getChinese(), op, language[0]));

                t.WriteConListtent(op, path3,
                        test1.LanguageDataTempCreate(total / 100 * gd.getLanguage().getJapanese(), op, language[1]));

                t.WriteConListtent(op, path3,
                        test1.LanguageDataTempCreate(total / 100 * gd.getLanguage().getEnglish(), op, language[2]));

                t.WriteConListtent(op, path3,
                        test1.LanguageDataTempCreate(total / 100 * gd.getLanguage().getOther_language(), op, language[3]));
            }
    }
    }

    public void writeHeader(String path, String option) throws IOException {
        File file = new File(path);
        BufferedWriter bwt = new BufferedWriter(new FileWriter(file));

        if (option.equals("kind")){
            bwt.write("Rate,publishedYear,commentNum,kind" + "\r\n");
            bwt.flush();
            bwt.close();
        }
        else if (option.equals("language")){
            bwt.write("Rate,publishedYear,commentNum,language,l_kind" + "\r\n");
            bwt.flush();
            bwt.close();
        }
        else {
            bwt.write("Rate,publishedYear,commentNum,location" + "\r\n");
            bwt.flush();
            bwt.close();
        }
    }


}
