/**
 * Copyright © 2002 Instituto Superior Técnico
 *
 * This file is part of FenixEdu Core.
 *
 * FenixEdu Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu Core.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.fenixedu.domain;

import java.util.Collection;

import net.sourceforge.fenixedu.domain.degree.DegreeType;
import net.sourceforge.fenixedu.domain.degreeStructure.CycleType;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;

public abstract class AcademicProgram extends AcademicProgram_Base {
    public AcademicProgram() {
        super();
    }

    public abstract DegreeType getDegreeType();

    public abstract Collection<CycleType> getCycleTypes();

    public void delete() {
        if (!getAcademicAuthorizationGroupSet().isEmpty()) {
            throw new DomainException("error.academicProgram.cannotDeleteAcademicProgramUsedInAccessControl");
        }
        setAdministrativeOffice(null);
        deleteDomainObject();
    }

}
