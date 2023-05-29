package com.bosgii.internshipmanagement.services;

import com.bosgii.internshipmanagement.entities.Instructor;
import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.enums.InternshipType;
import com.bosgii.internshipmanagement.repos.CompanyRepository;
import com.bosgii.internshipmanagement.repos.InstructorRepository;
import com.bosgii.internshipmanagement.repos.InternshipRepository;
import com.bosgii.internshipmanagement.repos.StudentRepository;
import com.bosgii.internshipmanagement.requests.GenerateFinalPDFRequest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.core.io.FileSystemResource;

import javax.xml.transform.Source;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class FinalPDFRequestService {

    public FinalPDFRequestService(){
    }

    public ResponseEntity<Resource> GenerateFinalPdf(Internship internship, GenerateFinalPDFRequest req){
        Long internshipId = internship.getId();
        int pointOfEmployer = req.getPointOfEmployer();
        boolean isWorkComp = req.getIsWorkComp();
        boolean isSupervisor = req.getIsSupervisorComp();
        ArrayList<String> pages = req.getPages();
        ArrayList<String> scores = req.getScores();
        int evaluationOfCompanyInstructor = req.getEvaluationOfCompanyByInstructor();

        int partCP1 = 0;
        String partCP1Str = "";
        try{
            partCP1 = Integer.parseInt(scores.get(0));
            partCP1Str = String.valueOf(partCP1);
        }catch (Exception e){}
        int partCP2 = 0;
        String partCP2Str = "";
        int partCP3 = 0;
        String partCP3Str = "";
        try{
            partCP3 = Integer.parseInt(scores.get(6));
            partCP3Str = String.valueOf(partCP3);
        }catch (Exception e){

        }
        try{
            for (String score : scores.subList(1,6))
                partCP2 += Integer.parseInt(score);
            partCP2Str = String.valueOf(partCP2);
        }catch (Exception e){

        }

        boolean overallSatisfactory = (partCP1>=7) && (partCP2>=25) && (partCP3 >= 7);
        boolean isPartAPassed = (pointOfEmployer>=7) && isWorkComp && isSupervisor;


        String pathOfDir = new FileSystemResource("").getFile().getAbsolutePath();
        Path rootDir = Path.of(pathOfDir);
        rootDir = rootDir.resolve("backend/documents").resolve("final_pdf").resolve(internshipId+".pdf");
        PdfWriter pdfWriter = null;

        try {
            pdfWriter = new PdfWriter(rootDir.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();
        Document document = new Document(pdfDocument);

        try {
            Paragraph header = new Paragraph("BILKENT UNIVERSITY")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(14).setFixedPosition(225F,800F,250F);

            Paragraph header2 = new Paragraph("Engineering Faculty" )
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(14).setFixedPosition(235F,780F,250F);

            Paragraph header3 = new Paragraph("Computer Engineering Department" )
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(14).setFixedPosition(180F,760F,250F);

            Paragraph header4 = new Paragraph("Summer Training Grade Form" )
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(14).setFixedPosition(200F,730F,250F);

            Paragraph header5 = new Paragraph("Confidential")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)).setUnderline(1F,-3F)
                    .setFontSize(12).setFixedPosition(265F,705F,250F);

            String whichInternship = "CS 299 [X]        CS 399 [ ]";
            if(internship.getType() == InternshipType.CS399)
                whichInternship = "CS 299 [ ]        CS 399 [X]";
            // determine which internship


            Paragraph header6 = new Paragraph("Name, Surname        : \nCompany name and department: \n Course                      : "+whichInternship )
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(11).setFixedPosition(55F,645F,350F);

            Paragraph partA = new Paragraph("Part-A: Work place").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(11).setFixedPosition(52F,619F,350F);

            Paragraph evolutionByEmployer = new Paragraph("Average of the grades on the Summer Training Evaluation Form\n" +
                    "(Staj Degerlendirme Formu) filled by the employer                                                  :________").setFontSize(11).setFixedPosition(52F,588F,450F);


            Paragraph caution = new Paragraph("To be satisfactory, average of the grades on the “Staj Değerlendirme Formu” must be at least 7.")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE))
                    .setFontSize(9).setFixedPosition(52F,578F,450F);

            Paragraph cmpEng = new Paragraph("Is the work done related to computer engineering? [Y/N]                                       : ________")
                    .setFontSize(11).setFixedPosition(52F,558F,450F);

            Paragraph supervisor = new Paragraph("Is the supervisor a computer engineer or\n" +
                    "has a similar engineering background? [Y/N]                                                         : ________")
                    .setFontSize(11).setFixedPosition(52F,518F,550F);

            Paragraph partASatis = new Paragraph("...... If all conditions in Part-A are satisfied, continue to Part-B, else mark Unsatisfactory in Overall Evaluation .......")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE))
                    .setFontSize(9).setFixedPosition(52F,497F,550F);

            Paragraph partB = new Paragraph("Part-B: Report             Satisfactory [X]            Revision required [ ]").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(11).setFixedPosition(52F,475F,550F);

            Paragraph partB1 = new Paragraph("If revision is required, changes needed must be stated on the report. The report is\n" +
                    "returned to the student until satisfactory.").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(11).setFixedPosition(52F,440F,550F).setFixedLeading(13F);

            Paragraph partB2 = new Paragraph("Due date for resubmission:                                                                                ....../......./20....")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(11).setFixedPosition(52F,415F,550F);

            Paragraph partB3 = new Paragraph("Student is given two weeks for each revision.                                                 To be set by the department secretary")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE))
                    .setFontSize(9).setFixedPosition(52F,405F,550F);

            Paragraph partBCond = new Paragraph("......... If the report in Part-B is Satisfactory, continue to Part-C, else return it to the student for Revision ..........")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE))
                    .setFontSize(9).setFixedPosition(52F,389F,550F);

            Paragraph partC = new Paragraph("Part-C: Final version of the report")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(11).setFixedPosition(52F,370F,550F);

            Paragraph partC1 = new Paragraph("Based on the final version of the report, as evaluated on the back side of this form:")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(11).setFixedPosition(52F,350F,550F);

            Paragraph partC2 = new Paragraph("Assessment/quality score of Evaluation of the Work - item (1)                               :_______")
                    .setFontSize(11).setFixedPosition(52F,330F,550F);;

            Paragraph partC3 = new Paragraph("Sum of the Assessment/quality scores of Evaluation of the Work - items (2)-(6)   :_______")
                    .setFontSize(11).setFixedPosition(52F,300F,550F);;

            Paragraph partC4 = new Paragraph("The Assessment/quality score of Evaluation of the Report                                    :_______")
                    .setFontSize(11).setFixedPosition(52F,270F,550F);

            Paragraph partC5 = new Paragraph("To be satisfactory, the score must be at least 7/10.")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE))
                    .setFontSize(9).setFixedPosition(52F,320F,550F);

            Paragraph partC6 = new Paragraph("To be satisfactory, the sum must be at least 25/50.")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE))
                    .setFontSize(9).setFixedPosition(52F,290F,550F);

            Paragraph partC7 = new Paragraph("To be satisfactory, the score must be at least 7/10.")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE))
                    .setFontSize(9).setFixedPosition(52F,260F,550F);


            Paragraph overall = new Paragraph("Overall Evaluation")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(14).setFixedPosition(52F,225F,550F).setUnderline(1F,-2F);


            String overAllSatisStr = "Satisfactory\u00B9 [ ]              Unsatisfactory\u00B2 [X]";
            if(isPartAPassed && overallSatisfactory)
                overAllSatisStr = "Satisfactory\u00B9 [X]              Unsatisfactory\u00B2 [ ]";

            Paragraph overallSatis = new Paragraph(overAllSatisStr)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(12).setFixedPosition(252F,225F,550F);

            Paragraph evaluator = new Paragraph("Evaluator:")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(12).setFixedPosition(52F,200F,550F);

            Paragraph name = new Paragraph("Name, Surname:\nSignature")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(12).setFixedPosition(252F,180F,550F);


            String evolName = "Instructor";
            Instructor instructor = internship.getInstructor();
            evolName = instructor.getFullName();
            Paragraph parOfEvolName = new Paragraph(evolName).setFontSize(12).setFixedPosition(352F,198F,100F);
            document.add(parOfEvolName);
            LocalDate currentDate = LocalDate.now();
            int day = currentDate.getDayOfMonth();
            int month = currentDate.getMonthValue();
            Paragraph date = new Paragraph("Date\n"+day+"/"+month+"/2023")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(12).setFixedPosition(452F,165F,550F).setFixedLeading(25F);

            Paragraph evaluationOfComp = new Paragraph("Evaluation of the Company/Department")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(14).setFixedPosition(52F,145,490F).setUnderline(1F,-2F).setBorderTop(new SolidBorder(2));

            String evolOfCompStr = "[  ] I strongly recommend this place for future students\n" +
                    "[  ] I am satisfied with this place\n" +
                    "[  ] I recommend this place not be allowed for future students.";
            if(evaluationOfCompanyInstructor == 1)
                evolOfCompStr = "[X] I strongly recommend this place for future students\n" +
                        "[  ] I am satisfied with this place\n" +
                        "[  ] I recommend this place not be allowed for future students.";
            else if(evaluationOfCompanyInstructor == 2)
                evolOfCompStr = "[  ] I strongly recommend this place for future students\n" +
                        "[X] I am satisfied with this place\n" +
                        "[  ] I recommend this place not be allowed for future students.";
            else if(evaluationOfCompanyInstructor == 3){
                evolOfCompStr = "[  ] I strongly recommend this place for future students\n" +
                        "[  ] I am satisfied with this place\n" +
                        "[X] I recommend this place not be allowed for future students.";
            }
            Paragraph evaluationOfComp2 = new Paragraph(evolOfCompStr)
                    .setFontSize(12).setFixedPosition(52F,95F,550F).setFixedLeading(15F);

            Paragraph appendix = new Paragraph("\u00B9 In order for the Summer Training be satisfactory, all the conditions in Part-A, Part-B and Part-C must be satisfied. " +
                    "\n\u00B2 In this case, the Summer Training has to be repeated.")
                    .setFontSize(9).setFixedPosition(52F,50F,490F).setFixedLeading(15F)
                    .setBorderBottom(new SolidBorder(1)).setBorderTop(new SolidBorder(1));




            Table table4 = new Table(3);
            table4.setPageNumber(2).setFixedPosition(55F,390F,485F).setBorder(new SolidBorder(3));
            Cell cell11 = new Cell();
            Cell cell12 = new Cell();
            Cell cell13 = new Cell();
            try {
                cell11.add(new Paragraph("Evaluation of the Work").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)))
                        .setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(12);
                cell12.add(new Paragraph("On what page(s) of the report is the evidence of this found?\u00B3")).setWidth(135F)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(11);
                cell13.add(new Paragraph("Assessment/quality score (from 0=missing to 10=full)"))
                        .setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(11);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            table4.addCell(cell11.setWidth(200F).setHeight(60F));
            table4.addCell(cell12);
            table4.addCell(cell13);

            table4.addCell(new Cell().add(
                            new Paragraph("(1) Able to perform work at the level expected from a summer training in the area of computer engineering. " ).setFontSize(11).setFixedLeading(13F))
                    .add(new Paragraph("(this is the evaluation of all the work done in the summer training)").setFontSize(11).setFixedLeading(13F)
                            .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE))));

            table4.addCell(new Cell().add(new Paragraph(pages.get(0)).setWidth(120F)));
            table4.addCell(new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .add(new Paragraph(String.valueOf(scores.get(0))).setMarginLeft(55F)
                            .setBold().setHorizontalAlignment(HorizontalAlignment.CENTER)));

            table4.addCell(new Cell().add(new Paragraph("(2) Solves complex engineering problems by applying principles of engineering, science, and mathematics.")
                    .setFontSize(11)));
            table4.addCell(new Cell().add(new Paragraph(pages.get(1)).setWidth(120F)));
            table4.addCell(new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .add(new Paragraph(String.valueOf(scores.get(1))).setMarginLeft(55F)
                            .setBold().setHorizontalAlignment(HorizontalAlignment.CENTER)));


            table4.addCell(new Cell().add(new Paragraph("(3) Recognizes ethical and professional responsibilities in engineering situations.")
                    .setFontSize(11)));
            table4.addCell(new Cell().add(new Paragraph(pages.get(2)).setWidth(120F)));
            table4.addCell(new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .add(new Paragraph(String.valueOf(scores.get(2))).setMarginLeft(55F)
                            .setBold().setHorizontalAlignment(HorizontalAlignment.CENTER)));

            table4.addCell(new Cell().add(new Paragraph("(4) Able to make informed judgments that consider the impact of engineering solutions in global, economic, environmental, and societal contexts.")
                    .setFontSize(11)));
            table4.addCell(new Cell().add(new Paragraph(pages.get(3)).setWidth(120F)));
            table4.addCell(new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .add(new Paragraph(String.valueOf(scores.get(3))).setMarginLeft(55F)
                            .setBold().setHorizontalAlignment(HorizontalAlignment.CENTER)));

            table4.addCell(new Cell().add(new Paragraph("(5) Able to acquire new knowledge using appropriate learning strategy or strategies.")
                    .setFontSize(11)));
            table4.addCell(new Cell().add(new Paragraph(pages.get(4)).setWidth(120F)));
            table4.addCell(new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .add(new Paragraph(String.valueOf(scores.get(4))).setMarginLeft(55F)
                            .setBold().setHorizontalAlignment(HorizontalAlignment.CENTER)));

            table4.addCell(new Cell().add(new Paragraph("(6) Able to apply new knowledge as needed.")
                    .setFontSize(11)));
            table4.addCell(new Cell().add(new Paragraph(pages.get(5)).setWidth(120F)));
            table4.addCell(new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .add(new Paragraph(String.valueOf(scores.get(5))).setMarginLeft(55F)
                            .setBold().setHorizontalAlignment(HorizontalAlignment.CENTER)));

            document.add(table4.setPageNumber(2));


            Table table5 = new Table(3);
            table5.setPageNumber(2).setFixedPosition(55F,170F,485F).setBorder(new SolidBorder(3));
            Cell cell511 = new Cell();
            Cell cell512 = new Cell();
            Cell cell513 = new Cell();
            try {
                cell511.add(new Paragraph("Evaluation of Report").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)))
                        .setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(12);
                cell512.add(new Paragraph("On what page(s) of the report is the counter evidence of this found?")).setWidth(135F)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(11);
                cell513.add(new Paragraph("Assessment/quality score (from 0=missing to 10=full)"))
                        .setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(11);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            table5.addCell(cell511.setWidth(200F).setHeight(60F));
            table5.addCell(cell512);
            table5.addCell(cell513);

            table5.addCell(new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("Able to prepare reports with high standards in terms of content, organization, style and language (the Summer Training report itself is to be evaluated)")
                    .setFontSize(11)).setHeight(130F));
            table5.addCell(new Cell().add(new Paragraph(pages.get(6)).setWidth(120F)));
            table5.addCell(new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .add(new Paragraph(String.valueOf(scores.get(6))).setMarginLeft(55F)
                            .setBold().setHorizontalAlignment(HorizontalAlignment.CENTER)));

            document.add(table5.setPageNumber(2));

            Paragraph appendix2 = new Paragraph("\u00B3  If you think that a question does not apply to this particular summer training, please write NA (not applicable).")
                    .setFontSize(9).setFixedPosition(52F,50F,490F).setFixedLeading(15F)
                    .setBorderBottom(new SolidBorder(1)).setBorderTop(new SolidBorder(1));

            document.add(appendix2.setPageNumber(2));


            document.add(header);
            document.add(header2);
            document.add(header3);
            document.add(header4);
            document.add(header5);
            document.add(header6);
            document.add(partA);
            document.add(evolutionByEmployer);
            document.add(caution);
            document.add(cmpEng);
            document.add(supervisor);
            document.add(partASatis);
            document.add(partB);
            document.add(partB1);
            document.add(partB2);
            document.add(partB3);
            document.add(partBCond);
            document.add(partC);
            document.add(partC1);
            document.add(partC2);
            document.add(partC3);
            document.add(partC4);
            document.add(partC5);
            document.add(partC6);
            document.add(partC7);
            document.add(overall);
            document.add(overallSatis);
            document.add(evaluator);
            document.add(name);
            document.add(date);
            document.add(evaluationOfComp);
            document.add(evaluationOfComp2);
            document.add(appendix);




        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Table table = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
        table.setBorder(new SolidBorder(2));
        table.setHeight(130F);
        table.setFixedPosition(45F,510F,new UnitValue(1,500F));
        document.add(table);

        Table table2 = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
        table2.setBorder(new SolidBorder(2));
        table2.setHeight(95F);
        table2.setFixedPosition(45F,402F,new UnitValue(1,500F));
        document.add(table2);

        Table table3 = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
        table3.setBorder(new SolidBorder(2));
        table3.setHeight(130F);
        table3.setFixedPosition(45F,259F,new UnitValue(1,500F));
        document.add(table3);







        String pathPNG = new FileSystemResource("").getFile().getAbsolutePath();
        Path root = Path.of(pathPNG);
        root = root.resolve("backend/documents").resolve("bilkent.png");

        try {
            ImageData imageData = ImageDataFactory.create(root.toString());
            Image image = new Image(imageData);
            image.scale(0.18F, 0.18F);
            image.setFixedPosition(55F,730F);
            document.add(image);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        /*String pathSign = new FileSystemResource("").getFile().getAbsolutePath();
        Path rootSign = Path.of(pathSign);
        rootSign = rootSign.resolve("backend/documents").resolve("signuture.png");

        try {
            ImageData imageDataSign = ImageDataFactory.create(rootSign.toString());
            Image imageSign = new Image(imageDataSign);
            imageSign.scale(0.14F,0.10F);
            imageSign.setFixedPosition(350F,170F);
            document.add(imageSign);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        */

        String name = internship.getStudent().getFullName();
        String companyDepartment = internship.getCompany().getName();


        Paragraph parOfname = new Paragraph(name).setFixedPosition(185F,677F,100F);
        document.add(parOfname);

        Paragraph parOfcomp = new Paragraph(companyDepartment).setFixedPosition(245F,660F,100F);
        document.add(parOfcomp);

        int evolPoint = pointOfEmployer;
        Paragraph parOfEvolPoint = null;
        try {
            parOfEvolPoint = new Paragraph(String.valueOf(evolPoint)).setFixedPosition(468F,588F,52F)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        document.add(parOfEvolPoint);

        String YN1 = "N";
        if(isWorkComp)
            YN1 = "Y";
        Paragraph parOfYN1 = null;
        try {
            parOfYN1 = new Paragraph(YN1).setFixedPosition(470F,558F,52F)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        document.add(parOfYN1);

        String YN2 = "Y";
        if(!isSupervisor)
            YN2 = "N";
        Paragraph parOfYN2 = null;
        try {
            parOfYN2 = new Paragraph(YN2).setFixedPosition(470F,518F,52F)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        document.add(parOfYN2);

        String point1 = partCP1Str;
        Paragraph parOfPoint1 = null;
        try {
            parOfPoint1 = new Paragraph(point1).setFixedPosition(462F,330F,52F)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        document.add(parOfPoint1);


        String point26 = partCP2Str;
        Paragraph partOfPoint26 = null;
        try {
            partOfPoint26 = new Paragraph(point26).setFixedPosition(462F,300F,52F)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        document.add(partOfPoint26);

        String pointLast = partCP3Str;
        Paragraph partOfPointLast = null;
        try {
            partOfPointLast = new Paragraph(pointLast).setFixedPosition(462F,270F,52F)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        document.add(partOfPointLast);
        document.close();
        Resource resource;

        try {
            resource = new UrlResource(rootDir.toUri());
            return new ResponseEntity<>(resource,HttpStatus.OK);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }





    }

    public String createPagesStr(ArrayList<Integer> list){
        String pages = "";
        for(int page : list){
            pages+=page + ", ";
        }
        return pages + "\b";
    }
}
