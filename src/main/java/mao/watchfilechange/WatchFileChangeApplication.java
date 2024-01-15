package mao.watchfilechange;

import mao.watchfilechange.utils.FileChangeListener;
import mao.watchfilechange.utils.FileChangeWatchUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class WatchFileChangeApplication
{
    private static final Logger log = LoggerFactory.getLogger(WatchFileChangeApplication.class);

    public static void main(String[] args)
    {
        SpringApplication.run(WatchFileChangeApplication.class, args);
        FileChangeWatchUtils.watch("./b.txt", new FileChangeListener()
        {
            @Override
            public void callback(String filePath)
            {
                System.out.println("b已修改");
            }
        });
        FileChangeWatchUtils.watch("./c.txt", filePath -> System.out.println("c已修改"));
        String file = FileChangeWatchUtils.class.getClassLoader().getResource("test.properties").getFile();
        file=file.substring(1);
        System.out.println(file);
        FileChangeWatchUtils.watch(file, filePath -> System.out.println("test.properties已修改"));
    }

}
