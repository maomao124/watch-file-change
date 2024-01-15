package mao.watchfilechange.run;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.io.watch.watchers.DelayWatcher;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * Project name(项目名称)：watch-file-change
 * Package(包名): mao.watchfilechange.run
 * Class(类名): AppRunner
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2024/1/15
 * Time(创建时间)： 14:18
 * Version(版本): 1.0
 * Description(描述)： 监听文件变化
 */


@Component
public class AppRunner implements ApplicationRunner
{
    private static final Logger log = LoggerFactory.getLogger(AppRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        startWatch();
    }

    //通过watch方式
    public void startWatch()
    {
        //监听文件修改
        Watcher watcher = new SimpleWatcher()
        {
            @Override
            public void onModify(WatchEvent<?> event, Path currentPath)
            {
                //重新记录文件修改时间
                log.info("监听到此文件修改:");
            }
        };
        //监听器,延迟500ms执行
        WatchMonitor monitor = WatchMonitor.createAll("./a.txt", new DelayWatcher(watcher, 500));
        log.info("监听文件修改事件：" + "./a.txt");
        monitor.start();
    }

    @PostConstruct
    public void init()
    {
        log.info("初始化 AppRunner");
    }
}
