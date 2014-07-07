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
package net.sourceforge.fenixedu.domain.serviceRequests.documentRequests;

import net.sourceforge.fenixedu.dataTransferObject.serviceRequests.AcademicServiceRequestBean;
import net.sourceforge.fenixedu.dataTransferObject.serviceRequests.DocumentRequestCreateBean;
import net.sourceforge.fenixedu.domain.accounting.EventType;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;

public class StandaloneEnrolmentCertificateRequest extends StandaloneEnrolmentCertificateRequest_Base {

    public StandaloneEnrolmentCertificateRequest() {
        super();
    }

    public StandaloneEnrolmentCertificateRequest(DocumentRequestCreateBean bean) {
        this();
        super.init(bean);
        checkParameters(bean);
        super.getEnrolmentsSet().addAll(bean.getEnrolments());
    }

    @Override
    protected void checkParameters(final DocumentRequestCreateBean bean) {
        if (bean.getEnrolments() == null || bean.getEnrolments().isEmpty()) {
            throw new DomainException("error.StandaloneEnrolmentCertificateRequest.no.enrolments");
        }
    }

    @Override
    public Integer getNumberOfUnits() {
        return super.getEnrolmentsSet().size();
    }

    @Override
    public DocumentRequestType getDocumentRequestType() {
        return DocumentRequestType.STANDALONE_ENROLMENT_CERTIFICATE;
    }

    @Override
    public String getDocumentTemplateKey() {
        return getClass().getName();
    }

    @Override
    public EventType getEventType() {
        return EventType.STANDALONE_ENROLMENT_APPROVEMENT_CERTIFICATE_REQUEST;
    }

    @Override
    public boolean isAvailableForTransitedRegistrations() {
        return true;
    }

    @Override
    public boolean hasPersonalInfo() {
        return true;
    }

    @Override
    protected void internalChangeState(AcademicServiceRequestBean academicServiceRequestBean) {
        super.internalChangeState(academicServiceRequestBean);

        if (academicServiceRequestBean.isToCancelOrReject()) {
            for (; !getEnrolmentsSet().isEmpty();) {
                removeEnrolments(getEnrolmentsSet().iterator().next());
            }
        }
    }

}
