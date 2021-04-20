package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.Note;
import com.helper.FactoryProvider;


public class SaveNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SaveNoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try{
			
			//title content fetch	
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		Note note = new Note(title,content,new Date());
		
		//System.out.println(note.getId()+":"+note.getTitle());
		
		//hibernate save();
		Session s=FactoryProvider.getFactory().openSession();
		Transaction tx= s.beginTransaction();
		s.save(note);
		tx.commit();
		s.close();
		response.setContentType("text/html");
	    PrintWriter out=response.getWriter();//PrintWriter: prints text data to a character stream. getWriter :Returns a PrintWriter object that can send character text to the client. ... The method normally receives the request and response objects, we then call the . getWriter() method for the response obj that gets us the stream on which we can write our output.
	    out.println("<h1 style='text-align:center;'>Note is added Successfully</h1>");
	    out.println("<h1 style='text-align:center;'><a href='all_notes.jsp'>View all notes</a></h1>");

		
		}catch(Exception e)
		{
			e.printStackTrace();//The printStackTrace() method in Java is a tool used to handle exceptions and errors.
		}
		
	}

}
