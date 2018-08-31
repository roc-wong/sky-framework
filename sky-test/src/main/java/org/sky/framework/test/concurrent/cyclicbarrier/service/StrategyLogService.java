package org.sky.framework.test.concurrent.cyclicbarrier.service;

import com.google.common.collect.Lists;
import org.joda.time.LocalDateTime;
import org.joda.time.Seconds;
import org.sky.framework.test.concurrent.cyclicbarrier.model.Strategy;

import java.util.List;
import java.util.Random;

/**
 * @author roc
 * @date 2018/03/14
 */
public class StrategyLogService {

    public List<Strategy> queryStrategies(LocalDateTime begin, LocalDateTime end){

        List<Strategy> strategies = Lists.newArrayList();

        int timeDifference = Seconds.secondsBetween(begin, end).getSeconds();

        for (int i = 1; i <= 10; i++) {
            Strategy strategy = new Strategy();
            strategy.setId(i);
            strategy.setCreateDate(begin.plusSeconds(new Random().nextInt(timeDifference)).toDate());

            strategies.add(strategy);
        }

        return strategies;
    }

}
