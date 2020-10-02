import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "submit", urlPatterns = "/submit")
public class SubmitServlet extends HttpServlet {

    private static class PARAMS{
        public static String ITEM_ID = "item_id";
        public static String ITEM_NAME = "item_name";
        public static String NAME = "name";
        public static String EMAIL = "email";
        public static String BID_AMOUNT = "bid_amount";
        public static String CHECKBOX = "checkbox";
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int item_id = Integer.parseInt(req.getParameter(PARAMS.ITEM_ID));
        double bid_amount = Double.parseDouble(req.getParameter(PARAMS.BID_AMOUNT));
        String item_name = req.getParameter(PARAMS.ITEM_NAME);
        String name = req.getParameter(PARAMS.NAME);
        String email = req.getParameter(PARAMS.EMAIL);
        boolean checkbox = req.getParameter(PARAMS.CHECKBOX)=="true";

        StringBuilder html = new StringBuilder();
        html.append("<h1 align=\"center\">");
        html.append("Bid Submitted</h1>");
        html.append("<p align=\"center\">You bid is now active. If your bid is successful, you will be notified within 24 hours of the close of bidding.</p>");
        html.append("<table align=\"center\">");
        html.append(getRow(item_name));
        html.append(getRow(String.format("Item ID: %s", item_id)));
        html.append((getRow(String.format("Name: %s", name))));
        html.append(getRow(String.format("Email Address: %s", email)));
        html.append(getRow(String.format("Bid Price: Rs. %s", bid_amount)));
        html.append(getRow(String.format("Auto-increment price: %s", checkbox)));
        html.append("</table>");

        PrintWriter out = resp.getWriter();
        out.write(html.toString());
    }


    private String getRow(String val){
        StringBuilder builder = new StringBuilder();
        builder.append("<tr>");
        builder.append("<td>"+val+"</td");
        builder.append("</tr>");
        return builder.toString();
    }
}
