package robot;
import java.io.File;
import net.sourceforge.tess4j.*;
public class Tess4jExample {
  public static void main(String[] args) { 
        File imageFile = new File("E:/t2.png");
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        instance.setDatapath("C:\\Users\\Yang\\Downloads\\-Tess4J-3.0-src\\Tess4J\\tessdata");
        instance.setLanguage("chi_sim");
        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
