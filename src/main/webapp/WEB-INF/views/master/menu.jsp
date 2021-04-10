<%--
- menu.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.Manuel" action="https://www.youtube.com/watch?v=845aepdpHm8&t=8s&ab_channel=JaviMart%C3%ADnez"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.Evelyn" action="https://www.arcasevilla.es/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.JaviV" action="https://www.blog.binaria.uno/2020/03/22/como-agregar-un-directorio-a-tu-path-en-linux/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.Gonzalo" action="https://professor-falken.com/mac/como-instalar-mariadb-mysql-usando-homebrew-en-tu-mac/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.Maribel" action="https://open.spotify.com/album/28adqly9V7wm5DFr7hlqtK?si=lv0oGBM1SLSiBxZ7A7U-wA&nd=1"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.JaviM" action="https://open.spotify.com/album/5pQwQRnQOuKrbVUVnGMEN4?si=-9ewcULTRHKaxRjYfdRrrQ"/>
			<acme:menu-suboption code="master.menu.anonymous.list-shout" action="/anonymous/shout/list"/>
			<acme:menu-suboption code="master.menu.anonymous.create-shout" action="/anonymous/shout/create"/>
			<acme:menu-suboption code="master.menu.anonymous.list-task" action="/anonymous/task/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.list-all-announcement" action="/administrator/announcement/list-all"/>
			<acme:menu-suboption code="master.menu.administrator.list-recent-announcement" action="/administrator/announcement/list-recent"/>
			<acme:menu-suboption code="master.menu.administrator.create-announcement" action="/administrator/announcement/create"/>
		    <acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/master/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/master/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link.Manuel" action="https://www.youtube.com/watch?v=845aepdpHm8&t=8s&ab_channel=JaviMart%C3%ADnez"/>
			<acme:menu-suboption code="master.menu.provider.favourite-link.Evelyn" action="https://www.arcasevilla.es/"/>
			<acme:menu-suboption code="master.menu.provider.favourite-link.JaviV" action="https://www.blog.binaria.uno/2020/03/22/como-agregar-un-directorio-a-tu-path-en-linux/"/>
			<acme:menu-suboption code="master.menu.provider.favourite-link.Gonzalo" action="https://professor-falken.com/mac/como-instalar-mariadb-mysql-usando-homebrew-en-tu-mac/"/>
			<acme:menu-suboption code="master.menu.provider.favourite-link.Maribel" action="https://open.spotify.com/album/28adqly9V7wm5DFr7hlqtK?si=lv0oGBM1SLSiBxZ7A7U-wA&nd=1"/>
			<acme:menu-suboption code="master.menu.provider.favourite-link.JaviM" action="https://open.spotify.com/album/5pQwQRnQOuKrbVUVnGMEN4?si=-9ewcULTRHKaxRjYfdRrrQ"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link.Manuel" action="https://www.youtube.com/watch?v=845aepdpHm8&t=8s&ab_channel=JaviMart%C3%ADnez"/>
			<acme:menu-suboption code="master.menu.consumer.favourite-link.Evelyn" action="https://www.arcasevilla.es/"/>
			<acme:menu-suboption code="master.menu.consumer.favourite-link.JaviV" action="https://www.blog.binaria.uno/2020/03/22/como-agregar-un-directorio-a-tu-path-en-linux/"/>
			<acme:menu-suboption code="master.menu.consumer.favourite-link.Gonzalo" action="https://professor-falken.com/mac/como-instalar-mariadb-mysql-usando-homebrew-en-tu-mac/"/>
			<acme:menu-suboption code="master.menu.consumer.favourite-link.Maribel" action="https://open.spotify.com/album/28adqly9V7wm5DFr7hlqtK?si=lv0oGBM1SLSiBxZ7A7U-wA&nd=1"/>
			<acme:menu-suboption code="master.menu.consumer.favourite-link.JaviM" action="https://open.spotify.com/album/5pQwQRnQOuKrbVUVnGMEN4?si=-9ewcULTRHKaxRjYfdRrrQ"/>
		</acme:menu-option>
				
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.tasks" action="/authenticated/task/list"/>
			<acme:menu-suboption code="master.menu.authenticated.list-announcement" action="/authenticated/announcement/list"/>
		</acme:menu-option>
		
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

