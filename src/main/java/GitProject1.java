import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GitProject1
 */
@WebServlet("/GitProject1")
public class GitProject1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String GITHUB_USERNAME = "rahulbangera";
	private static final String GITHUB_REPO = "javaJSFLab";
	private static final String TOKEN = "YOURSECRET";
		
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GitProject1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		String apiUrl = "https://api.github.com/repos/" + GITHUB_USERNAME  + "/" + GITHUB_REPO + "/" + "traffic/views";
		
		URL url = new URL(apiUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/vnd.github+json");
		conn.setRequestProperty("Authorization", "Bearer " + TOKEN);
		
		int status = conn.getResponseCode();
		
		if(status == 200)
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuilder content = new StringBuilder();
			while((inputLine = in.readLine()) != null)
			{
				content.append(inputLine);
			}
			in.close();
			conn.disconnect();
			
			response.getWriter().println("<h1>Github Views");
			response.getWriter().println("<pre>" + content.toString() + "</pre>");
		}
		else
		{
			response.getWriter().println("<h1>Error fetching data</h1>");
			response.getWriter().println("Response Code: " + status);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
