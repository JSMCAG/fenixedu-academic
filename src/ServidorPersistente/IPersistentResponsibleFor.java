/*
 * Created on 26/Mar/2003
 *
 * 
 */
package ServidorPersistente;

import java.util.List;

import Dominio.IDisciplinaExecucao;
import Dominio.IResponsibleFor;
import Dominio.ITeacher;
import ServidorPersistente.exceptions.ExistingPersistentException;

/**
 * @author Jo�o Mota
 *
 *
 */
public interface IPersistentResponsibleFor extends IPersistentObject {
	public List readByTeacher(ITeacher teacher) throws ExcepcaoPersistencia;
	public IResponsibleFor readByTeacherAndExecutionCourse(
		ITeacher teacher,
		IDisciplinaExecucao executionCourse)
		throws ExcepcaoPersistencia;
	public List readByExecutionCourse(IDisciplinaExecucao executionCourse)
		throws ExcepcaoPersistencia;
	public void delete(IResponsibleFor responsibleFor)
		throws ExcepcaoPersistencia;
	public void deleteAll() throws ExcepcaoPersistencia;
	public List readAll()throws ExcepcaoPersistencia;
	public void lockWrite(IResponsibleFor responsibleFor)
		throws ExcepcaoPersistencia, ExistingPersistentException;
}
