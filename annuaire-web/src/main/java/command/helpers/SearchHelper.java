package command.helpers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class SearchHelper extends AbstractHelper{
    public SearchHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public Map<String, String> getSearchParams(){
        Map<String, String> params = new HashMap<>();

        params.put("firstName", request.getParameter("firstName"));
        params.put("lastName", request.getParameter("lastName"));
        params.put("middleName", request.getParameter("middleName"));
        params.put("dateRange", request.getParameter("dateRadio"));
        params.put("dateOfBirth", request.getParameter("dateOfBirth"));
        params.put("sex", request.getParameter("sex"));
        params.put("citizenship", request.getParameter("citizenship"));
        params.put("maritalStatus", request.getParameter("maritalStatus"));
        params.put("country", request.getParameter("country"));
        params.put("city", request.getParameter("city"));
        params.put("address", request.getParameter("address"));
        params.put("zip", request.getParameter("zip"));

        return params;
    }
}