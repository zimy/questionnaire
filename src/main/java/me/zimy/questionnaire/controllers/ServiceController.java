package me.zimy.questionnaire.controllers;

import me.zimy.questionnaire.Reporter;
import me.zimy.questionnaire.domain.Question;
import me.zimy.questionnaire.domain.Responder;
import me.zimy.questionnaire.services.QuestionService;
import me.zimy.questionnaire.services.ResponderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/7/14.
 */
@Controller
@RequestMapping(value = "/services")
public class ServiceController {
    @Autowired
    Reporter reporter;
    @Autowired
    private ResponderService responderService;
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    @ResponseBody
    public List<Question> getAllQuestions() {
        return questionService.getAll();
    }

    @RequestMapping(value = "/responders", method = RequestMethod.GET)
    @ResponseBody
    public List<Responder> getAllResponders() {
        return responderService.getAll();
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    @ResponseBody
    public String requestEmailReport() {
        reporter.sendEmailReport();
        return "You will get reported soon.";
    }

    @RequestMapping(value = "/report/get", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File report = reporter.getReportAsFile();
        Path path = Paths.get(report.getAbsolutePath());
        String mimeType = request.getServletContext().getMimeType(report.getAbsolutePath());
        response.setContentType(mimeType == null ? "application/octet-stream" : mimeType);
        response.setHeader("Content-Disposition", "inline; filename=\"report.ods\"");
        try (OutputStream stream = response.getOutputStream()) {
            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            if (attributes.isRegularFile()) {
                response.setContentLength((int) attributes.size());
                Files.copy(path, stream);
            }
        }
    }
}
