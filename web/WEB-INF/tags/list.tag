<%@tag description="A tag to list items" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="list" required="true" type="java.util.List"%>
<%@attribute name="page" required="true"%>
<%@attribute name="nbPage" required="true"%>
<%@attribute name="perPage" %>
<%@attribute name="var" required="true" rtexprvalue="false"%>

<%@attribute name="paginationUrl" required="true"%>

<%@attribute name="listClass"%>
<%@attribute name="itemClass"%>

<c:set var="perPage" value="${(empty perPage) ? list.size() : perPage}"/>
<c:set var="listClass" value="${(empty listClass) ? 'list-group' : listClass}"/>
<c:set var="itemClass" value="${(empty listClass) ? 'list-group-item' : itemClass}"/>

<c:choose>
    
    <c:when test="${ empty list}" >
        <div>
            Pas de resultats
        </div>
    </c:when>

    <c:otherwise>
        <%-- On parcourt la liste --%>
        <ul class="${ listClass }">

            <c:forEach varStatus="status" var="item" items="${ list }" end="${ perPage }">

                <%@ variable alias="current" name-from-attribute="var" scope="NESTED" %>
                <c:set var="current" value="${ item }" />

                <li class="${ itemClass } clearfix">

                    <jsp:doBody />

                </li>
            </c:forEach>
        </ul>
        
        <c:if test="${ nbPage > 1 }">
        <%-- Pagination --%>
        <div class="text-center">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li class="<c:if test="${ page < 2 }">disabled</c:if>">
                        <c:url value="${ paginationUrl }" var="url">
                            <c:param name="page" value="${ 1 }" />
                            <c:param name="perPage" value="${ perPage }" />
                        </c:url>
                        <a href="${ url }" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>

                    <c:forEach varStatus="status" begin="1" end="${ nbPage }">

                        <c:url value="${ paginationUrl }" var="url">
                            <c:param name="page" value="${ status.index }" />
                            <c:param name="perPage" value="${ perPage }" />
                        </c:url>

                        <li class="<c:if test="${ status.index == page }"> active </c:if>">
                            <a href="<c:out value="${ url }"/>"><c:out value="${ status.index }"/></a>
                        </li>
                    </c:forEach>

                    <li class="<c:if test="${ page >= nbPage}">disabled</c:if>">
                        <c:url value="${ paginationUrl }" var="url">
                            <c:param name="page" value="${ nbPage }" />
                            <c:param name="perPage" value="${ perPage }" />
                        </c:url>
                        <a href="${ url }" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div><%-- Fin pagination --%>
        </c:if>
    </c:otherwise>
    
</c:choose>
