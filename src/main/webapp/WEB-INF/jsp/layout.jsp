<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<table border="1" cellpadding="2" cellspacing="2" align="center">
    <tr>
        <td height="30" colspan="2" align="center"><tiles:insertAttribute name="header"/>
        </td>
    </tr>
    <tr>
        <td height="450"><tiles:insertAttribute name="menu"/></td>
        <td width="550"><tiles:insertAttribute name="body"/></td>
    </tr>
    <tr>
        <td height="30" colspan="2" align="center"><tiles:insertAttribute name="footer"/>
        </td>
    </tr>
</table>