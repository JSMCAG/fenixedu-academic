/*
 * Created on 6/Mai/2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package ServidorAplicacao.Servico.publico;

import DataBeans.ISiteComponent;
import DataBeans.InfoExecutionDegree;
import DataBeans.SiteView;
import DataBeans.util.Cloner;
import Dominio.ICursoExecucao;
import Dominio.IExecutionPeriod;
import Dominio.IExecutionYear;
import Dominio.ITurma;
import Dominio.Turma;
import ServidorAplicacao.FenixServiceException;
import ServidorAplicacao.IServico;
import ServidorAplicacao.Factory.PublicSiteComponentBuilder;
import ServidorPersistente.ExcepcaoPersistencia;
import ServidorPersistente.ICursoExecucaoPersistente;
import ServidorPersistente.IDisciplinaExecucaoPersistente;
import ServidorPersistente.IPersistentExecutionPeriod;
import ServidorPersistente.IPersistentExecutionYear;
import ServidorPersistente.ISuportePersistente;
import ServidorPersistente.ITurmaPersistente;
import ServidorPersistente.OJB.SuportePersistenteOJB;

/**
 * @author Jo�o Mota
 *
 * 
 */
public class ClassSiteComponentService implements IServico {

	private static ClassSiteComponentService _servico =
		new ClassSiteComponentService();

	/**
	  * The actor of this class.
	  **/

	private ClassSiteComponentService() {

	}

	/**
	 * Returns Service Name
	 */
	public String getNome() {
		return "ClassSiteComponentService";
	}

	/**
	 * Returns the _servico.
	 * @return ReadExecutionCourse
	 */
	public static ClassSiteComponentService getService() {
		return _servico;
	}

	public Object run(
		ISiteComponent bodyComponent,
		String executionYearName,
		String executionPeriodName,
		String degreeInitials,
		String nameDegreeCurricularPlan,
		String className,
		Integer curricularYear)
		throws FenixServiceException {

		SiteView siteView = null;
		InfoExecutionDegree infoExecutionDegree = null;
		try {
			ISuportePersistente sp = SuportePersistenteOJB.getInstance();
			IDisciplinaExecucaoPersistente persistentExecutionCourse =
				sp.getIDisciplinaExecucaoPersistente();
			IPersistentExecutionPeriod persistentExecutionPeriod =
				sp.getIPersistentExecutionPeriod();
			IPersistentExecutionYear persistentExecutionYear =
				sp.getIPersistentExecutionYear();

			ICursoExecucaoPersistente executionDegreeDAO =
				sp.getICursoExecucaoPersistente();

			IExecutionYear executionYear =
				persistentExecutionYear.readExecutionYearByName(
					executionYearName);

			IExecutionPeriod executionPeriod =
				persistentExecutionPeriod.readByNameAndExecutionYear(
					executionPeriodName,
					executionYear);

			ICursoExecucao executionDegree =
				executionDegreeDAO
					.readByDegreeInitialsAndNameDegreeCurricularPlanAndExecutionYear(
					degreeInitials,
					nameDegreeCurricularPlan,
					executionYear);
			if (executionDegree != null) {
				infoExecutionDegree =
					Cloner.copyIExecutionDegree2InfoExecutionDegree(
						executionDegree);
			}
			PublicSiteComponentBuilder componentBuilder =
				PublicSiteComponentBuilder.getInstance();

			ITurma domainClass =
				getDomainClass(
					className,
					curricularYear,
					executionPeriod,
					executionDegree,
					sp);
			bodyComponent =
				componentBuilder.getComponent(bodyComponent, domainClass);
			siteView = new SiteView(bodyComponent);
		} catch (ExcepcaoPersistencia e) {
			throw new FenixServiceException(e);
		}

		return siteView;
	}

	private ITurma getDomainClass(
		String className,
		Integer curricularYear,
		IExecutionPeriod executionPeriod,
		ICursoExecucao executionDegree,
		ISuportePersistente sp)
		throws ExcepcaoPersistencia, FenixServiceException {

		ITurmaPersistente persistentClass = sp.getITurmaPersistente();
		ITurma domainClass = null;

		if (curricularYear == null) {
			domainClass =
				persistentClass.readByNameAndExecutionDegreeAndExecutionPeriod(
					className,
					executionDegree,
					executionPeriod);

		} else {
			domainClass = new Turma();
			domainClass.setAnoCurricular(curricularYear);
			domainClass.setExecutionDegree(executionDegree);
			domainClass.setExecutionPeriod(executionPeriod);

		}
		return domainClass;
	}
}
