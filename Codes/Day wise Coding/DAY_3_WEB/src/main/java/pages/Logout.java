package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/exit")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		System.out.println("Inside doGET of Logout!");
		try(PrintWriter pw = response.getWriter())
		{
			pw.print("<!DOCTYPE html>"
					+ "<html lang=\"en\">"
					+ ""
					+ "<head>"
					+ "    <meta charset=\"UTF-8\">"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
					+ "    <title>Logout - Voting Portal</title>"
					+ "    <style>"
					+ "        body {"
					+ "            font-family: Arial, sans-serif;"
					+ "            background-color: #f4f4f4;"
					+ "            margin: 0;"
					+ "            padding: 0;"
					+ "            display: flex;"
					+ "            justify-content: center;"
					+ "            align-items: center;"
					+ "            height: 100vh;"
					+ "        }"
					+ ""
					+ "        .logout-container {"
					+ ""
					+ "            width: 40%;"
					+ "            text-align: center;"
					+ "            background-color: #fff;"
					+ "            padding: 20px;"
					+ "            border-radius: 8px;"
					+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);"
					+ "        }"
					+ ""
					+ "        h1 {"
					+ "            color: #007BFF;"
					+ "        }"
					+ ""
					+ "        p { "
					+ "            color: #555;"
					+ "        }"
					+ ""
					+ "        a {"
					+ "            color: #007BFF;"
					+ "            text-decoration: none;"
					+ "        }"
					+ ""
					+ "        a:hover {"
					+ "            text-decoration: underline;"
					+ "        }"
					+ ""
					+ "        footer{"
					+ "			text-align: center;"
					+ "			width: 100%;"
					+ "			background-color: rgb(84, 147, 255);"
					+ "			color: white;"
					+ "			font-size: medium;"
					+ "			position: fixed;"
					+ "			bottom: 0;"
					+ "		}"
					+ "    </style>"
					+ "</head>"
					+ ""
					+ "<body>"
					+ "    <div class=\"logout-container\">"
					+ "        <h1>Logout Successful!</h1>"
					+ "        <p>You have been successfully logged out of the Voting Portal.</p>"
					+ "        <p>Thank you for using our services.</p>"
					+ "        <p>Return to <a href=\"loginPage.html\">Login Page</a></p>"
					+ "    </div>"
					+ "    <footer>"
					+ "		<div>Designed by @Jayesh Murodiya</div>"
					+ "	</footer>"
					+ "</body>"
					+ ""
					+ "</html>"
					+ "");
		}

	}

}
