package org.hzero.order32953.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hzero.boot.imported.app.service.BatchImportHandler;
import org.hzero.boot.imported.infra.validator.annotation.ImportService;
import org.hzero.order32953.app.service.SoLineService;
import org.hzero.order32953.domain.entity.SoHeader;
import org.hzero.order32953.domain.repository.SoHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据导入
 *
 * @author 32953 2021-08-18 18:19:22
 */
@Slf4j
@ImportService(templateCode = "HODR.32953.IMP")
public class OrderImportService extends BatchImportHandler {

    private final ObjectMapper objectMapper;
    private final SoHeaderRepository soHeaderRepository;
    private final SoLineService soLineService;

    @Autowired
    public OrderImportService(ObjectMapper objectMapper, SoHeaderRepository soHeaderRepository, SoLineService soLineService) {
        this.objectMapper = objectMapper;
        this.soHeaderRepository = soHeaderRepository;
        this.soLineService = soLineService;
    }

    @Override
    public Boolean doImport(List<String> datas) {
        datas.forEach((data) -> {
            System.out.println("=================" + data);
        });
        List<SoHeader> soHeaders = new ArrayList<>();
//        List<SoLine> soLines = new ArrayList<>();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        for (int index = 0; index < datas.size(); index++) {
            try {
                soHeaders.add(objectMapper.readValue(datas.get(index), SoHeader.class));
            } catch (IOException e) {
                log.error("数据转换失败");
                addErrorMsg(index, "这个数据有问题");
                return false;
            }
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>" + soHeaders);
//        soHeaderRepository.batchInsert(soHeaders);
        return true;
    }

    @Override
    public int getSize(){
        return 0;
    }

}
