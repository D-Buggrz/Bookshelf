/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.travelers.basicactions;

import com.google.gson.Gson;
import com.travelers.daos.LocationDao;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [START example]
@SuppressWarnings("serial")
@WebServlet(name = "delete", value = "/delete")
public class DeleteLocationServlet extends HttpServlet {
	  private static final Logger logger = Logger.getLogger(DeleteLocationServlet.class.getName());
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    Long id = Long.decode(req.getParameter("id"));
    LocationDao dao = (LocationDao) this.getServletContext().getAttribute("dao");
    try {
      dao.deleteLocation(id);
      logger.log(Level.INFO, "Content Type: " + req.getContentType());
      if (req.getContentType() != null && "application/json".equalsIgnoreCase(req.getContentType())
      		|| "json".equalsIgnoreCase(req.getParameter("format"))) {
      	//Use GSon to convert your objects to a String.
      	resp.getWriter().write("Location " + id + "has been deleted!");
      	resp.flushBuffer();
      } else {
    	  resp.sendRedirect("/locations");
      }
    } catch (Exception e) {
      throw new ServletException("Error deleting location", e);
    }
  }
}
// [END example]
