package pis.projekt.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class JSONHandler {
    public static int getCountFromJSON(@NotNull JSONObject json){
      return json.getInt("count");
    }

    public static String getPDFReadableContent(byte[] pdfBytes){
        try (PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfBytes))) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "well something went wrong, hasn't it? :)";
    }
}
