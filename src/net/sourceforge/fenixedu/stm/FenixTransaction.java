package net.sourceforge.fenixedu.stm;

import org.apache.ojb.broker.PersistenceBroker;

import jvstm.VBoxBody;

interface FenixTransaction extends Comparable<FenixTransaction> {
    int getNumber();
    void finish();
    boolean isFinished();
    DBChanges getDBChanges();
    PersistenceBroker getOJBBroker();
    <T> VBoxBody<T> getBodyForRead(VBox<T> vbox, Object obj, String attr);
    <T> VBoxBody<T> getBodyInTx(VBox<T> vbox);
}
