package com.testautomation.framework.utils;

import com.testautomation.framework.base.ConfigTestData;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ScreenshotGenarator {

    ConfigTestData configTestData=null;
    public ScreenshotGenarator(ConfigTestData configTestData){
        this.configTestData=configTestData;
    }
    public String getScreenshot(String path) throws FileNotFoundException {
        String screenshotPath = null;

        if(path == null) {
            String screenshotFileName = configTestData.testMethodName + "_[" + DateAndTime.getTime() + "]_" + DateAndTime.getDate();
            screenshotPath = Generic.readConfigProp("extent.screenshots.path") + screenshotFileName + ".png";
            path = ConfigTestData.workDir+Generic.readConfigProp("extent.htmlreports.file") + screenshotPath;

        }

        File file = new File(path);
        path = screenshotPath;

        byte[] screenshot = new byte[0];

        AShot shot = new AShot();
        shot = shot.shootingStrategy(ShootingStrategies.viewportPasting(100));
        Screenshot screen = shot.takeScreenshot(configTestData.driver);
        BufferedImage originalImage = screen.getImage();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "png", baos);
            baos.flush();
            screenshot = baos.toByteArray();
            baos.close();
            InputStream in = new ByteArrayInputStream(screenshot);
            BufferedImage image = ImageIO.read(in);

            ImageIO.write(image, "png", file);
        } catch (Exception noScreenshot) {
        }
        return path;
    }
}
