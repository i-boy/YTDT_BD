package com.iesvn.yte.paging;

import static org.jboss.seam.ScopeType.SESSION;

import java.util.ArrayList;

import javax.faces.event.ActionEvent;

import org.ajax4jsf.component.html.HtmlAjaxCommandLink;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.richfaces.event.DataScrollerEvent;

@Name("testBean")
@Scope(SESSION)
public class TestBean implements java.io.Serializable {

	private ArrayList data;
	private ArrayList data1;
	private ArrayList data2;
	private ArrayList data3;
	private ArrayList data4;
	private ArrayList data5;
	private ArrayList data6;
	public boolean renderIfSinglePage;
	public int rows;
	public int maxpage;
    private int actionCount;
    private int eventCount;


    public TestBean () {
        renderIfSinglePage=true;

	        renderIfSinglePage=true;
          	rows=5;
	        maxpage=10;

		data = new ArrayList();
        for (int i = 0; i < 10; i++) {
            data.add(new Entry(null, i));
        }

		data1 = new ArrayList();
        for (int i = 0; i < 11; i++) {
            data1.add(new Entry(null, i));
        }

		data2 = new ArrayList();
        for (int i = 0; i < 12; i++) {
            data2.add(new Entry(null, i));
        }

		data3 = new ArrayList();
        for (int i = 0; i < 13; i++) {
            data3.add(new Entry(null, i));
        }

		data4 = new ArrayList();
        for (int i = 0; i < 14; i++) {
            data4.add(new Entry(null, i));
        }

		data5 = new ArrayList();
        for (int i = 0; i < 15; i++) {
            data5.add(new Entry(null, i));
        }

		data6 = new ArrayList();
        for (int i = 0; i < 16; i++) {
            data6.add(new Entry(null, i));
        }


	}

    /**
     * ActionListener to test {@link http://jira.jboss.com/jira/browse/RF-1834}
     * 
     * @param e - ActionEvent
     */
    public void doSort(ActionEvent e){
        HtmlAjaxCommandLink a = (HtmlAjaxCommandLink)e.getComponent();
    }

     public void setRenderIfSinglePage(boolean renderIfSinglePage){
		this.renderIfSinglePage = renderIfSinglePage;
     }

     public boolean isRenderIfSinglePage() {
		return this.renderIfSinglePage;
     }

     public void setRows(int rows){

 		this.rows = rows;
      }

      public int getRows() {

 		return this.rows;
      }

     public void setMaxpage(int maxpage){

 		this.maxpage = maxpage;
      }

      public int getMaxpage() {
 		return this.maxpage;
      }

      public void onAction(ActionEvent actionEvent) {
          actionCount++;
      }


    public void doScroll(DataScrollerEvent event) {

        String oldScrolVal = event.getOldScrolVal();
        String newScrolVal = event.getNewScrolVal();
        eventCount++;
   }


	public ArrayList getData() {
		return data;
	}



	public void setData(ArrayList data) {
		this.data = data;
	}


	public ArrayList getData1() {
		return data1;
	}



	public void setData1(ArrayList data) {
		this.data1 = data;
	}
	public ArrayList getData2() {
		return data2;
	}



	public void setData2(ArrayList data) {
		this.data2 = data;
	}
	public ArrayList getData3() {
		return data3;
	}



	public void setData3(ArrayList data) {
		this.data3 = data;
	}
	public ArrayList getData4() {
		return data4;
	}



	public void setData4(ArrayList data) {
		this.data4 = data;
	}
	public ArrayList getData5() {
		return data5;
	}



	public void setData5(ArrayList data) {
		this.data5 = data;
	}

	public ArrayList getData6() {
		return data6;
	}

	public void setData6(ArrayList data) {
		this.data6 = data;
	}

    public int getActionCount() {
        return actionCount;
    }

    public void setActionCount(int actionCount) {
        this.actionCount = actionCount;
    }

    public int getEventCount() {
        return eventCount;
    }

    public void setEventCount(int eventCount) {
        this.eventCount = eventCount;
    }

    public void addEntries() {
    	for (int i = 0; i < 5; i++) {
			data.add(new Entry(null, data.size()));
		}
    }

    public void removeEntries() {
    	if (data.size() >= 5) {
        	for (int i = 0; i < 5; i++) {
    			data.remove(data.size() - 1);
    		}
    	}
    } 
}

