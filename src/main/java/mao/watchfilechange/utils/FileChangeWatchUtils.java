package mao.watchfilechange.utils;

import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.io.watch.watchers.DelayWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.function.Consumer;

/**
 * Project name(项目名称)：watch-file-change
 * Package(包名): mao.watchfilechange.utils
 * Class(类名): FileChangeWatchUtils
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2024/1/15
 * Time(创建时间)： 15:05
 * Version(版本): 1.0
 * Description(描述)： 文件修改监听工具类
 */

public class FileChangeWatchUtils
{
    private static final Logger log = LoggerFactory.getLogger(FileChangeWatchUtils.class);

    public static void watch(String filePath, FileChangeListener fileChangeListener)
    {
        //监听文件修改
        Watcher watcher = new SimpleWatcher()
        {
            @Override
            public void onModify(WatchEvent<?> event, Path currentPath)
            {
                //重新记录文件修改时间
                log.info("监听到此文件修改:"+ filePath);
                //执行回调方法
                fileChangeListener.callback(filePath);
            }
        };
        //监听器,延迟500ms执行
        WatchMonitor monitor = WatchMonitor.createAll(filePath, new DelayWatcher(watcher, 500));
        log.info("监听文件修改事件：" + filePath);
        monitor.start();
    }

}


