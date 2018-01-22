/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inw;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lukasz Szymkowski "Simon Goatbeard"
 */
public class InwRaport {

    Connection con;
    Statement stmt;
    ResultSet rs;
    PrintWriter zapis;
    String str = "";
    Conn myConn = new Conn();
    
    public void inwRaport() {
        con=myConn.connectMe();
        stmt=myConn.connectStatement(con);
        Date currentDate = new Date();
        SimpleDateFormat dateFormatFile = new SimpleDateFormat("dd.MM.yyyy HH-mm-ss");
        String dateString = dateFormatFile.format(currentDate);
        String path = "Raport " + dateString + ".xls";
        File filw = new File(path);
        try {

            zapis = new PrintWriter(path, "UTF-8");

        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(InwentaryzacjaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<?mso-application progid=\"Excel.Sheet\"?>\n"
                + "<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\" xmlns:c=\"urn:schemas-microsoft-com:office:component:spreadsheet\" xmlns:html=\"http://www.w3.org/TR/REC-html40\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\" xmlns:x2=\"http://schemas.microsoft.com/office/excel/2003/xml\" xmlns:x=\"urn:schemas-microsoft-com:office:excel\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                + "    <OfficeDocumentSettings xmlns=\"urn:schemas-microsoft-com:office:office\">\n"
                + "        <Colors>\n"
                + "            <Color>\n"
                + "                <Index>3</Index>\n"
                + "                <RGB>#000000</RGB>\n"
                + "            </Color>\n"
                + "            <Color>\n"
                + "                <Index>4</Index>\n"
                + "                <RGB>#006600</RGB>\n"
                + "            </Color>\n"
                + "            <Color>\n"
                + "                <Index>5</Index>\n"
                + "                <RGB>#333333</RGB>\n"
                + "            </Color>\n"
                + "            <Color>\n"
                + "                <Index>6</Index>\n"
                + "                <RGB>#808080</RGB>\n"
                + "            </Color>\n"
                + "            <Color>\n"
                + "                <Index>7</Index>\n"
                + "                <RGB>#996600</RGB>\n"
                + "            </Color>\n"
                + "            <Color>\n"
                + "                <Index>8</Index>\n"
                + "                <RGB>#c0c0c0</RGB>\n"
                + "            </Color>\n"
                + "            <Color>\n"
                + "                <Index>9</Index>\n"
                + "                <RGB>#cc0000</RGB>\n"
                + "            </Color>\n"
                + "            <Color>\n"
                + "                <Index>10</Index>\n"
                + "                <RGB>#ccffcc</RGB>\n"
                + "            </Color>\n"
                + "            <Color>\n"
                + "                <Index>11</Index>\n"
                + "                <RGB>#dddddd</RGB>\n"
                + "            </Color>\n"
                + "            <Color>\n"
                + "                <Index>12</Index>\n"
                + "                <RGB>#ffcccc</RGB>\n"
                + "            </Color>\n"
                + "            <Color>\n"
                + "                <Index>13</Index>\n"
                + "                <RGB>#ffffcc</RGB>\n"
                + "            </Color>\n"
                + "            <Color>\n"
                + "                <Index>14</Index>\n"
                + "                <RGB>#ffffff</RGB>\n"
                + "            </Color>\n"
                + "        </Colors>\n"
                + "    </OfficeDocumentSettings>\n"
                + "    <ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">\n"
                + "        <WindowHeight>9000</WindowHeight>\n"
                + "        <WindowWidth>13860</WindowWidth>\n"
                + "        <WindowTopX>240</WindowTopX>\n"
                + "        <WindowTopY>75</WindowTopY>\n"
                + "        <ProtectStructure>False</ProtectStructure>\n"
                + "        <ProtectWindows>False</ProtectWindows>\n"
                + "    </ExcelWorkbook>\n"
                + "    <Styles>\n"
                + "        <Style ss:ID=\"Default\" ss:Name=\"Default\"/>\n"
                + "        <Style ss:ID=\"Heading_20__28_user_29_\" ss:Name=\"Heading (user)\">\n"
                + "            <Font ss:Bold=\"1\" ss:Color=\"#000000\" ss:Size=\"24\"/>\n"
                + "        </Style>\n"
                + "        <Style ss:ID=\"Heading_20_1\" ss:Name=\"Heading 1\">\n"
                + "            <Font ss:Bold=\"1\" ss:Color=\"#000000\" ss:Size=\"18\"/>\n"
                + "        </Style>\n"
                + "        <Style ss:ID=\"Heading_20_2\" ss:Name=\"Heading 2\">\n"
                + "            <Font ss:Bold=\"1\" ss:Color=\"#000000\" ss:Size=\"12\"/>\n"
                + "        </Style>\n"
                + "        <Style ss:ID=\"Text\" ss:Name=\"Text\"/>\n"
                + "        <Style ss:ID=\"Note\" ss:Name=\"Note\">\n"
                + "            <Font ss:Color=\"#333333\" ss:Size=\"10\"/>\n"
                + "        </Style>\n"
                + "        <Style ss:ID=\"Footnote\" ss:Name=\"Footnote\">\n"
                + "            <Font ss:Color=\"#808080\" ss:Italic=\"1\" ss:Size=\"10\"/>\n"
                + "        </Style>\n"
                + "        <Style ss:ID=\"Status\" ss:Name=\"Status\"/>\n"
                + "        <Style ss:ID=\"Good\" ss:Name=\"Good\">\n"
                + "            <Font ss:Color=\"#006600\" ss:Size=\"10\"/>\n"
                + "        </Style>\n"
                + "        <Style ss:ID=\"Neutral\" ss:Name=\"Neutral\">\n"
                + "            <Font ss:Color=\"#996600\" ss:Size=\"10\"/>\n"
                + "        </Style>\n"
                + "        <Style ss:ID=\"Bad\" ss:Name=\"Bad\">\n"
                + "            <Font ss:Color=\"#cc0000\" ss:Size=\"10\"/>\n"
                + "        </Style>\n"
                + "        <Style ss:ID=\"Warning\" ss:Name=\"Warning\">\n"
                + "            <Font ss:Color=\"#cc0000\" ss:Size=\"10\"/>\n"
                + "        </Style>\n"
                + "        <Style ss:ID=\"Error\" ss:Name=\"Error\">\n"
                + "            <Font ss:Bold=\"1\" ss:Color=\"#ffffff\" ss:Size=\"10\"/>\n"
                + "        </Style>\n"
                + "        <Style ss:ID=\"Accent\" ss:Name=\"Accent\">\n"
                + "            <Font ss:Bold=\"1\" ss:Color=\"#000000\" ss:Size=\"10\"/>\n"
                + "        </Style>\n"
                + "        <Style ss:ID=\"Accent_20_1\" ss:Name=\"Accent 1\">\n"
                + "            <Font ss:Bold=\"1\" ss:Color=\"#ffffff\" ss:Size=\"10\"/>\n"
                + "        </Style>\n"
                + "        <Style ss:ID=\"Accent_20_2\" ss:Name=\"Accent 2\">\n"
                + "            <Font ss:Bold=\"1\" ss:Color=\"#ffffff\" ss:Size=\"10\"/>\n"
                + "        </Style>\n"
                + "        <Style ss:ID=\"Accent_20_3\" ss:Name=\"Accent 3\">\n"
                + "            <Font ss:Bold=\"1\" ss:Color=\"#000000\" ss:Size=\"10\"/>\n"
                + "        </Style>\n"
                + "        <Style ss:ID=\"co1\"/>\n"
                + "        <Style ss:ID=\"co2\"/>\n"
                + "        <Style ss:ID=\"co3\"/>\n"
                + "        <Style ss:ID=\"co4\"/>\n"
                + "        <Style ss:ID=\"ta1\"/>\n"
                + "    </Styles>\n"
                + "    <ss:Worksheet ss:Name=\"Arkusz1\">\n"
                + "        <Table ss:StyleID=\"ta1\">\n"
                + "            <Column ss:Width=\"22.337\"/>\n"
                + "            <Column ss:Width=\"64.0063\"/>\n"
                + "            <Column ss:Width=\"83.3386\"/>\n"
                + "            <Column ss:Width=\"84.1039\"/>\n"
                + "            <Row ss:Height=\"12.8126\">\n"
                + "                <Cell>\n"
                + "                    <Data ss:Type=\"String\">LP</Data>\n"
                + "                </Cell>\n"
                + "                <Cell>\n"
                + "                    <Data ss:Type=\"String\">Oddział</Data>\n"
                + "                </Cell>\n"
                + "                <Cell>\n"
                + "                    <Data ss:Type=\"String\">Ilość komputerów</Data>\n"
                + "                </Cell>\n"
                + "                <Cell ss:Index=\"4\"/>\n"
                + "            </Row>\n";

        String Computers;
        Computers = "Select Department.DEPNAME, Count(INV_DEVICES.ID) AS CountComputers from Department,INV_DEVICES Where Department.Department_ID=INV_DEVICES.CELL AND INV_DEVICES.TYPE='Komputer' GROUP BY Department.DEPNAME";
int count=0;
        try {
            rs = stmt.executeQuery(Computers);
            while (rs.next()) {
                ++count;
                str += "            <Row ss:Height=\"12.8126\">\n"
                        + "                <Cell>\n"
                        + "                    <Data ss:Type=\"Number\">" + count + "</Data>\n"
                        + "                </Cell>\n"
                        + "                <Cell>\n"
                        + "                    <Data ss:Type=\"String\">" + rs.getString("DEPNAME") + "</Data>\n"
                        + "                </Cell>\n"
                        + "                <Cell>\n"
                        + "                    <Data ss:Type=\"String\">" + rs.getString("CountComputers") + "</Data>\n"
                        + "                </Cell>\n"
                        + "                <Cell ss:Index=\"4\"/>\n"
                        + "            </Row>\n";
            }
        } catch (SQLException ex) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }
        str += "        </Table>\n"
                + "        <x:WorksheetOptions/>\n"
                + "    </ss:Worksheet>\n"
                + "</Workbook>";

        zapis.println(str);
        zapis.close();
        try {
            Desktop.getDesktop().open(filw);
        } catch (IOException ex) {
            Logger.getLogger(InwOrzeczenie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
