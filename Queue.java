
package sample;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ressay
 */
public class Queue
{
    LinkedList<QueueElement> elements;
    QueueElement currentElement = null;
    String medID = "1";
    
    public Queue(String meID)
    {
        medID = meID;
        loadQueue();
    }
    
    public Queue()
    {
        elements = new LinkedList<>();
    }
    
    public Queue(LinkedList<QueueElement> e)
    {
        elements = e;
        for(int i=0;i<e.size();i++)
            e.get(i).makeDirtyAdd();
        manageQueue();
        if(elements.size()>0)
            updateDB();
    }
    
    void reservePlace(QueueElement e)
    {
        if(!elementExists(e))
        {
            e.setIDState(0, QueueElement.ReservedOnly);
            addElement(e);
        }
        getElement(e).setDateReservation(new Date());
    }
    
    boolean elementExists(QueueElement e)
    {
        for(int i=0;i<elements.size();i++)
            if(e.ID.compareTo( elements.get(i).ID)==0)
                return true;
        return false;
    }
    
    QueueElement getElement(QueueElement e)
    {
        for(int i=0;i<elements.size();i++)
            if(elements.get(i).ID.compareTo(e.ID)==0)
                return elements.get(i);
        return null;
    }
    
    void arrivedAtPlace(QueueElement e)
    {
        if(!elementExists(e))
        {
            e.setIDState(0, QueueElement.Arrived);
            addElement(e);
        }
        getElement(e).setDateArrived(new Date());
        manageQueue();
        updateAll(getElement(e));
    }
    
    void addElement(QueueElement e)
    {
        elements.add(e);
        e.makeDirtyAdd();
        updateDB();        
    }
    
    public boolean passNextElement()
    {
        System.out.println("sample.Queue.PassNextElement()entered");
        if(elements.size()>0 && elements.get(0).getStateElement() == QueueElement.Arrived)
        passElement(elements.getFirst());
        else if(currentElement != null)
        {
            currentElement.setDateFinished(new Date());
            updateAll(currentElement);
            currentElement = null;
            return false;
        }
        else
            return false;
        return true;
    }
    
    void passElement(QueueElement e)
    {
        if(currentElement != null)
        {
            currentElement.setDateFinished(new Date());
            //currentElement.makeDirtyUpdate();
            updateAll(currentElement);
        }
        currentElement = e;
        currentElement.setDatePassedDoc(new Date());
        deleteElement(currentElement);
        manageQueue();
    }
    
    void removeFromQueue(QueueElement e)
    {
        getElement(e).setDateFinished(new Date());
        deleteElement(getElement(e));
    }
    
    void deleteElement(QueueElement e)
    {
        if(this.elementExists(e))
        {
        e.makeDirtyRemove();
        updateDB();
        }
    }
    
    QueueElement getNextArrived()
    {
        QueueElement min = null;
        for(int i=0;i<elements.size();i++)
            if(elements.get(i).getStateElement() == QueueElement.Arrived)
                if(min == null || min.getRealDate().after(elements.get(i).getRealDate()))
                    min = elements.get(i);
        return min;
    }
    
    void orderQueue()
    {
        if(elements.size() == 0)
            return;
        int k;
        QueueElement min = null;
        for(int i=0;i<elements.size();i++)
        {
            k=i;
            min = elements.get(i);
            for(int j=i+1;j<elements.size();j++)
                if(min.getRealDate().after(elements.get(i).getRealDate()))
                {
                    k=j;
                    min = elements.get(j);
                }
            elements.set(k, elements.get(i));
            elements.set(i, min);
        }
    }
    
    boolean cmprLists(LinkedList<QueueElement> l1,LinkedList<QueueElement> l2)
    {
        if(l1.size() != l2.size())
            return false;
        else
            for(int i=0;i<l1.size();i++)
                if(!l1.get(i).compare(l2.get(i)))
                    return false;
        return true;
    }
    
    boolean updateElements()
    {
        LinkedList<QueueElement> ls = getListFromDB();
        if(cmprLists(elements, ls))
            return false;
        else
        {
            elements = ls;
            manageQueue();
            return true;
        }
        
    }
    
    int getNumberOfClients()
    {
        return elements.size();
    }
    int getNumberOfClientsArrived()
    {
        int S=0;
        for(int i=0;i<elements.size();i++)
            if(elements.get(i).getStateElement() == QueueElement.Arrived)
                S++;
        return S;
    }
    int getNumberOfClientsReserved()
    {
        int S=0;
        for(int i=0;i<elements.size();i++)
            if(elements.get(i).getStateElement() == QueueElement.ReservedOnly)
                S++;
        return S;
    }
    LinkedList<QueueElement> getListFromDB()
    {
        LinkedList<QueueElement> ls = new LinkedList<>();
                Map<String,String> m = new HashMap<>();
                m.put("op", "1");
                m.put("id_medecin", medID);
            
                //System.out.println("reponse: "+ QueryUtils.makeHttpPostRequest(QueryUtils.URL_TQueue, m));
                String output=QueryUtils.makeHttpPostRequest(QueryUtils.URL_TQueue, m);
                if(output == null || output.length() == 0)
                    return new LinkedList<>();
                System.out.println(output);
                String[] tabop=output.split("[*]");
                for(int i=0;i<tabop.length;i++)
                    tabop[i] += "#1";
                String[][] tab = new String[tabop.length][];
                
                for(int i=0;i<tabop.length;i++)
                {
                  tab[i] = tabop[i].split("#");
                }
                
                for(int i=0;i<tab.length;i++)
                {
                    if(tab[i].length != 9)
                        System.out.println("error in " + i);
                    //id_q id_med date_reserve date_arrive date_entree datesorte idpat codepq
                    //public QueueElement(String id,String dateR,String dateA,String dateE,String dateS,String idPat,String k) 
                    QueueElement e = new QueueElement(tab[i][0], tab[i][2], tab[i][3], tab[i][4], tab[i][5], tab[i][6], tab[i][7]);
                    if(e.getDatePassedDoc() != null)
                        currentElement = e;
                    else
                        ls.add(e);
                    
                        //System.out.println("info "+ e.getStateElement() + " " + e.ID +": "+e.getRealDate());
                }
                
                
                
           
            return ls;
    }
    
    void loadQueue()
    {
        elements = getListFromDB();
        manageQueue();
    }
    
    void manageQueue()
    {
        orderQueue();
        if(elements.size()>0 && elements.get(0).getStateElement() == QueueElement.ReservedOnly)
        {
            QueueElement elem = getNextArrived();
            if(elem == null)
                return;
            elements.remove(elem);
            elements.add(0, elem);
        }
    }
    
    void addInDataBase(QueueElement e)
    {
                if(e.getStateElement() == QueueElement.Arrived)
                {
                    createArrived(e);
                }
                else
                {
                    createReserved(e,"2");
                }

    }
    
    void createArrived(QueueElement e)
    {
                Map<String,String> m = new HashMap<>();
                String date = QueueElement.dateToString(e.getRealDate());
                System.out.println("date is: khobz"+date);
                m.put("date_arrivee", date);
                m.put("op", "3");
                m.put("id_medecin", medID);
            
                System.out.println("sample.Queue.updateDB()");
                e.ID = QueryUtils.makeHttpPostRequest(QueryUtils.URL_TQueue, m);
                System.out.println(e.ID);
                //e.getStateElement() == QueueElement.Arrived
          
    }
    
    void createReserved(QueueElement e,String patID)
    {
                Map<String,String> m = new HashMap<>();
                String date = QueueElement.dateToString(e.getRealDate());
                System.out.println("date is: khobz"+date);
                m.put("date_reserve", date);
                m.put("op", "4");
                m.put("id_patient", patID);
                m.put("id_medecin", medID);
           
                System.out.println("sample.Queue.updateDB()");
                e.ID = QueryUtils.makeHttpPostRequest(QueryUtils.URL_TQueue, m);
                System.out.println(e.ID);
            
    }

    
    void updateAll(QueueElement e)
    {
                Map<String,String> m = new HashMap<>();
                
                String date1 = null;
                if(e.getDateArrived()!=null)
                date1= QueueElement.dateToString(e.getDateArrived());
                String date2 = null;
                if(e.getDateFinished()!=null)
                date2 = QueueElement.dateToString(e.getDateFinished());
                String date3 = null;
                if(e.getDatePassedDoc()!=null)
                date3 = QueueElement.dateToString(e.getDatePassedDoc());
                String date4 = null;
                if(e.getDateReservation()!=null)
                date4 = QueueElement.dateToString(e.getDateReservation());
                System.out.println("update 1 is: "+date1);
                System.out.println("update 2 is: "+date2);
                System.out.println("update 3 is: "+date3);
                System.out.println("update 4 is: "+date4);
                System.out.println("update 5 is: "+e.ID);
                System.out.println("update 6 is: "+medID);
                m.put("date_arrivee", date1);
                m.put("date_sortie", date2);
                m.put("date_entree", date3);
                m.put("date_reserve", date4);
                
                m.put("op", "2");
                m.put("id_q", e.ID);
                m.put("id_medecin", medID);
            
                System.out.println(QueryUtils.makeHttpPostRequest(QueryUtils.URL_TQueue, m));
           
    }
    
    void deleteFromDataBase()
    {
        
    }
    
    void updateDB()
    {
        for(int i=0;i<elements.size();i++)
            if(elements.get(i).isDirtyAdd())
            {
                addInDataBase(elements.get(i));
                elements.get(i).notDirtyAdd();
            }
            else if(elements.get(i).isDirtyRemove())
            {
                updateAll(elements.get(i));
                System.out.println("sample.Queue.updateDB()khobz");
                elements.get(i).notDirtyRemove();
                elements.remove(elements.get(i));
                
            }
            else if(elements.get(i).isDirtyUpdate())
            {
                updateAll(elements.get(i));
                elements.get(i).notDirtyUpdate();
            }
    }
    
    void showList()
    {
        if(currentElement != null)
        {
            System.out.println("current element is: ");
            System.out.println(currentElement);
        }
        for(int i=0;i<elements.size();i++)
            System.out.println("element n°"+(i+1)+" "+elements.get(i));
    }

}
