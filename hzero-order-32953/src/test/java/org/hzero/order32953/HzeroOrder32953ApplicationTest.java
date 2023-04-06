package org.hzero.order32953;

import org.hzero.mybatis.domian.Condition;
import org.hzero.mybatis.util.Sqls;
import org.hzero.order32953.domain.entity.SoHeader;
import org.hzero.order32953.domain.repository.SoHeaderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 测试类
 *
 * @author 32953 2021-08-18 15:12:56
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HzeroOrder32953ApplicationTest {

    @Autowired
    private SoHeaderRepository soHeaderRepository;

    @Test
    public void testSelect() {
//        SoHeader select = soHeaderRepository.selectOne(new SoHeader().setCompanyId(2L));
//        System.out.println("===>>>>>>>" + select);
        Sqls custom1 = Sqls.custom();
        custom1.andEqualTo(SoHeader.FIELD_SO_HEADER_ID, 1L);
        Sqls custom2 = Sqls.custom();
        custom2.andEqualTo(SoHeader.FIELD_SO_HEADER_ID, 17L);
        List<SoHeader> soHeaders = soHeaderRepository.selectByCondition(Condition.builder(SoHeader.class).andWhere(custom1).orWhere(custom2).build());
        soHeaders.forEach(System.out::println);
    }

    /**
     * 测试时间对比
     */
    @Test
    public void time() throws ParseException {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//
//        Calendar calendar2019 = Calendar.getInstance();
//        calendar2019.set(Calendar.YEAR, 2019);
//        boolean before = calendar.after(calendar2019);
//        System.out.println(before);
//        boolean before = DateCompare.before("2011-08-26 11:26:25", "2019-01-01", "yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int i = calendar.get(Calendar.YEAR);
        System.out.println(i);
    }

}
