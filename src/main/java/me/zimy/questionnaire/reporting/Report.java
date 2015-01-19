package me.zimy.questionnaire.reporting;

import org.apache.commons.math3.util.Pair;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import javax.swing.table.TableModel;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 1/11/15.
 */
public class Report {
    List<Pair<String, TableModel>> pages;

    public Report() {
        pages = new ArrayList<>(10);
    }

    public void addPage(String name, TableModel model) {
        pages.add(new Pair<>(name, model));
    }

    public synchronized void export(Path path) throws IOException {
        SpreadSheet report = SpreadSheet.create(pages.size(), 1, 1);
        for (int i = 0; i < pages.size(); i++) {
            Sheet sheet = report.getSheet(i);
            sheet.setName(pages.get(i).getFirst());
            sheet.merge(pages.get(i).getSecond(), 0, 0, true);
        }
        report.saveAs(path.toFile());
    }
}
