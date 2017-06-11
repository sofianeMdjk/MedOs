/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ressay
 */
public class QueueElement implements Infos
{
    static final int ReservedOnly = 1;
    static final int Arrived = 2;
    static final int Current = 3;
    static final int Finished = 4;
    boolean dirtyAdd=false,dirtyRemove=false,dirtyUpdate=false;
    String ID="";
    String key = "not existing";
    String patID = "unknown";
    int stateElement = 0,startedState=0;
    String name=null;


    Date dateReservation = null;
    Date dateArrived = null;
    Date datePassedDoc = null;
    Date dateFinished = null;



    public QueueElement() 
    {
        dirtyAdd = false;
        dirtyRemove = false;
    }
    
    boolean compare(QueueElement q)
    {
        return (ID == q.ID && (dateArrived == q.dateArrived || getDateArrived().equals(q.getDateArrived()))
                && (datePassedDoc == q.datePassedDoc || getDatePassedDoc().equals(q.getDatePassedDoc()))
               && (dateFinished == q.dateFinished || getDateFinished().equals(q.getDateFinished())) );
    }
    
    public QueueElement(String id,String dateR,String dateA,String dateE,String dateS,String idPat,String k) 
    {
        ID = id;
        patID = idPat;
        key = k;
        startedState = QueueElement.Arrived;
        stateElement = QueueElement.Arrived;
        if(dateA.length()>0)
        dateArrived = stringToDate(dateA);
        else
        stateElement = QueueElement.ReservedOnly;
        
        if(dateR.length()>0)
        {
        dateReservation = stringToDate(dateR);
        startedState = QueueElement.ReservedOnly;
        }
        if(dateE.length()>0)
        {
        datePassedDoc = stringToDate(dateE);
        }
        if(dateS.length()>0)
        dateFinished = stringToDate(dateS);
        dirtyAdd = false;
        dirtyRemove = false;
    }
    
    public QueueElement(int id) 
    {
        ID = id+"";
        dirtyAdd = false;
        dirtyRemove = false;
    }
    
    public QueueElement(int id,int state) 
    {
        setIDState(id, state);
    }
    
    void setIDState(int id,int state) 
    {
        ID = id+"";
        stateElement = state;
        if(state == ReservedOnly)
        {
            setDateReservation(new Date());
            startedState = state;
        }
        else
        {
            setDateArrived(new Date());
            startedState = state;
        }
        dirtyAdd = false;
        dirtyRemove = false;
    }
            

    QueueElement(String string, String string0, String string1, String string2, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    void makeDirtyAdd()
    {
        dirtyAdd = true;
    }
    
    void makeDirtyRemove()
    {
        dirtyRemove = true;
    }
    
    void makeDirtyUpdate()
    {
        dirtyUpdate = true;
    }
    
    void notDirtyAdd()
    {
        dirtyAdd = false;
    }
    
    void notDirtyRemove()
    {
        dirtyRemove = false;
    }
    
    void notDirtyUpdate()
    {
        dirtyUpdate = false;
    }
    
    boolean isDirtyUpdate()
    {
        return dirtyUpdate;
    }
    
    boolean isDirtyAdd()
    {
        return dirtyAdd;
    }
    
    boolean isDirtyRemove()
    {
        return dirtyRemove;
    }
    
    
    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) 
    {
        stateElement = ReservedOnly;
        this.dateReservation = dateReservation;
    }

    public Date getDateArrived() 
    {
        return dateArrived;
    }

    public void setDateArrived(Date dateArrived) 
    {
        stateElement = Arrived;
        this.dateArrived = dateArrived;
    }

    public Date getDatePassedDoc() {
        return datePassedDoc;
    }

    public void setDatePassedDoc(Date datePassedDoc) 
    {
        stateElement = Current;
        this.datePassedDoc = datePassedDoc;
    }

    public Date getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(Date dateFinished) 
    {
        stateElement = Finished;
        this.dateFinished = dateFinished;
    }
    
    public int getStateElement() 
    {
        return stateElement;
    }
    
    public Date getRealDate()
    {
        if(getDateReservation() != null)
            return dateReservation;
        else
            return dateArrived;
    }
    
    static Date stringToDate(String s)
    {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime=null;
        try {
            dateTime = format.parse(s);
        } catch (ParseException ex) {
            Logger.getLogger(QueueElement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dateTime;
    }
    
    static String dateToString(Date d)
    {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s=format.format(d);
        System.out.println("date to string: "+s);
        return s;
    }
    
    @Override
    public String toString()
    {
        String here="";
        if(isHere())
            here = "is available";
        if(name == null)
        return "\t\t\t\t"+" ID: " + ID + " "+here;
        else
        return "\tdate: " + getRealDate() + " ID: " + ID + " Mr."+name;
    }

    @Override
    public String getInfo() 
    {
        return toString();
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isHere() 
    {
        return getStateElement() == QueueElement.Arrived;
    }
    
}
