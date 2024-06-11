package org.example.tictactoe_servlet;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "helloServlet", value = "/login")
public class ServletErklaerung extends HttpServlet {

    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Sagt dem Browser, dass es eine HTML verwenden wird.
        response.setContentType("text/html");

        // Bereitet vor, um Text auf die Webseite zu schreiben.
        PrintWriter out = response.getWriter();

        // Beginnt mit dem Schreiben der Webseite.
        out.println("<html><body>");
        out.println("<h1>Willkommen auf meiner Seite!</h1>");

        // Schaut nach, ob ein Name in der URL steht und grüßt die Person damit.
        String name = request.getParameter("name");
        if (name != null) {
            out.println("<h2>Hallo, " + name + "!</h2>");
        } else {
            out.println("<h2>Hallo, Gast!</h2>");
        }

        // Zeigt, welchen Browser der Besucher verwendet.
        String userAgent = request.getHeader("User-Agent");
        out.println("<p>Ihr Browser: " + userAgent + "</p>");

        // Fügt eine Info zur Webseite hinzu, die zeigt, dass unser Servlet sie gemacht hat.
        response.setHeader("X-Powered-By", "MeinServlet");

        // Teilt mit, dass alles in Ordnung ist (die Seite wurde richtig geladen).
        response.setStatus(HttpServletResponse.SC_OK);

        // Endet das Schreiben der Webseite.
        out.println("</body></html>");

        // Beendet das Schreiben.
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Setzt den Typ der Webseite auf HTML.
        response.setContentType("text/html");

        // Bereitet vor, um Text auf die Webseite zu schreiben.
        PrintWriter out = response.getWriter();

        // Beginnt mit dem Schreiben der Webseite.
        out.println("<html><body>");
        out.println("<h1>Ergebnisse des Formulars</h1>");

        // Holt Daten, die vom Benutzer im Formular eingegeben wurden.
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // Zeigt die eingegebenen Daten auf der Webseite an.
        if (name != null && email != null) {
            out.println("<p>Name: " + name + "</p>");
            out.println("<p>Email: " + email + "</p>");
        } else {
            out.println("<p>Fehler: Es fehlen einige Informationen.</p>");
        }

        // Speichert Daten oder führt weitere Aktionen aus (Platzhalter für Ihre Logik).
        // Hier können Sie Code einfügen, um die Daten in einer Datenbank zu speichern oder weitere Verarbeitungen vorzunehmen.

        // Setzt den Status der Antwort auf OK (200).
        response.setStatus(HttpServletResponse.SC_OK);

        // Endet das Schreiben der Webseite.
        out.println("</body></html>");

        // Beendet das Schreiben.
        out.close();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Setzt den Typ der Antwort auf HTML.
        response.setContentType("text/html");

        // Bereitet vor, um Text auf die Webseite zu schreiben.
        PrintWriter out = response.getWriter();

        // Beginnt mit dem Schreiben der Webseite.
        out.println("<html><body>");
        out.println("<h1>Update-Ergebnisse</h1>");

        // Holt Daten, die vom Benutzer gesendet wurden.
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // Zeigt die aktualisierten Daten auf der Webseite an.
        if (name != null && email != null) {
            out.println("<p>Name: " + name + " wurde aktualisiert.</p>");
            out.println("<p>Email: " + email + " wurde aktualisiert.</p>");
        } else {
            out.println("<p>Fehler: Es fehlen einige Informationen für das Update.</p>");
        }

        // Hier könnte man die Daten in einer Datenbank aktualisieren.

        // Setzt den Status der Antwort auf OK (200).
        response.setStatus(HttpServletResponse.SC_OK);

        // Endet das Schreiben der Webseite.
        out.println("</body></html>");

        // Beendet das Schreiben.
        out.close();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Setzt den Typ der Antwort auf HTML.
        response.setContentType("text/html");

        // Bereitet vor, um Text auf die Webseite zu schreiben.
        PrintWriter out = response.getWriter();

        // Beginnt mit dem Schreiben der Webseite.
        out.println("<html><body>");
        out.println("<h1>Lösch-Ergebnisse</h1>");

        // Holt die ID der zu löschenden Ressource aus der Anfrage.
        String resourceId = request.getParameter("id");

        // Überprüft, ob eine ID bereitgestellt wurde.
        if (resourceId != null) {
            // Hier könnte man die Ressource in einer Datenbank löschen.
            // Zum Beispiel: deleteResource(resourceId);

            out.println("<p>Ressource mit ID: " + resourceId + " wurde gelöscht.</p>");
            // Setzt den Status der Antwort auf OK (200).
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            out.println("<p>Fehler: Keine ID angegeben.</p>");
            // Setzt den Status der Antwort auf Bad Request (400).
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        // Endet das Schreiben der Webseite.
        out.println("</body></html>");

        // Beendet das Schreiben.
        out.close();
    }

    public void destroy() {
    }

}