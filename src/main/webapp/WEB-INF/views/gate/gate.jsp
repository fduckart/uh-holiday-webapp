<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class='container-fluid'>
    <!-- 
    <div class='row'>
        <div class='col-xs-offset-1 col-xs-5'>
            <div ng-controller="HolidayJsController" data-ng-init="init()">
                <div class='form-group'>
                    <label class='control-label' for='sta1'>Status</label>
                    <select class='form-control' ng-model='obj.sta1'>
                        <option ng-repeat="h in holidays">{{h}}</option>
                    </select>
                </div>                
            </div>
        </div>
        <div class='col-xs-7'>
            &nbsp;
        </div>
    </div>
    -->            
    <div class='row'>
        <div class='col-xs-offset-1 col-xs-11'>

          <table width="100%" border="0" cellpadding="10" cellspacing="0">
            <tr>
              <td>Year : <select name="displayYear" onchange="document.HolidayForm.submit();"><option value="2012" selected="selected">2012</option>
                <option value="2013">2013</option>
                <option value="2014">2014</option>
                <option value="2015">2015</option>
                <option value="2016">2016</option>
                <option value="2017">2017</option>
                <option value="2018">2018</option>
                <option value="2019">2019</option>
                <option value="2020">2020</option></select></td>
            </tr>
            <tr>
            <td>
                <table border="0" cellspacing="0" cellpadding="3" width="560">
                <tr>
                  <td nowrap class="headerRow">Holiday</td>
                  <td width="100" nowrap class="headerRow" colspan="2">Observed Date</td>
                  <td width="100" nowrap class="headerRow" colspan="2">Official Date</td>
                </tr>
                    <tr nowrap>
                    <td nowrap>New Year's Day</td>
                        <td colspan="2">01/02/2012</td>
                        <td colspan="2">01/01/2012</td>
                    </tr>
                    <tr nowrap>
                    <td nowrap>Dr. Martin Luther King, Jr. Day</td>
                        <td colspan="2">01/16/2012</td>
                        <td colspan="2">01/16/2012</td>
                    </tr>
                    <tr nowrap>
                    <td nowrap>Presidents' Day</td>
                        <td colspan="2">02/20/2012</td>
                        <td colspan="2">02/20/2012</td>
                    </tr>
                    <tr nowrap>
                    <td nowrap>Prince Jonah Kuhio Kalanianaole Day</td>
                        <td colspan="2">03/26/2012</td>
                        <td colspan="2">03/26/2012</td>
                    </tr>
                    <tr nowrap>
                    <td nowrap>Good Friday</td>
                        <td colspan="2">04/06/2012</td>
                        <td colspan="2">04/06/2012</td>
                    </tr>
                    <tr nowrap>
                    <td nowrap>Memorial Day</td>
                        <td colspan="2">05/28/2012</td>
                        <td colspan="2">05/28/2012</td>
                    </tr>
                    <tr nowrap>
                    <td nowrap>King Kamehameha I Day</td>
                        <td colspan="2">06/11/2012</td>
                        <td colspan="2">06/11/2012</td>
                    </tr>
                    <tr nowrap>
                    <td nowrap>Independence Day</td>
                        <td colspan="2">07/04/2012</td>
                        <td colspan="2">07/04/2012</td>
                    </tr>
                    <tr nowrap>
                    <td nowrap>Statehood Day</td>
                        <td colspan="2">08/17/2012</td>
                        <td colspan="2">08/17/2012</td>
                    </tr>
                    <tr nowrap>
                    <td nowrap>Labor Day</td>
                        <td colspan="2">09/03/2012</td>
                        <td colspan="2">09/03/2012</td>                    
                    </tr>                      
                    <tr nowrap>
                    <td nowrap>General Election Day</td>
                        <td colspan="2">11/06/2012</td>
                        <td colspan="2">11/06/2012</td>
                    </tr>
                    <tr nowrap>
                    <td nowrap>Veterans' Day</td>
                        <td colspan="2">11/12/2012</td>
                        <td colspan="2">11/11/2012</td>
                    </tr>
                    <tr nowrap>

                    <td nowrap>Thanksgiving</td>
                        <td colspan="2">11/22/2012</td>
                        <td colspan="2">11/22/2012</td>
                    </tr>
                <tr nowrap>
                    <td nowrap>Christmas</td>
                        <td colspan="2">12/25/2012</td>
                        <td colspan="2">12/25/2012</td>
                    </tr>                                              
              </table>
              </td>
            </tr>
          </table>

          
        </div>
    </div>
</div>
          