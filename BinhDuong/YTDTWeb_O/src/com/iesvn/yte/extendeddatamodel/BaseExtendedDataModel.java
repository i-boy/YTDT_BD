package com.iesvn.yte.extendeddatamodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.ajax4jsf.model.SerializableDataModel;


/**
 * 
 * @author ias
 * This is example class that intended to demonstrate use of ExtendedDataModel and SerializableDataModel.
 * This implementation intended to be used as a request scope bean. However, it actually provides serialized
 * state, so on a post-back we do not load data from the data provider. Instead we use data that was used 
 * during rendering.
 * This data model must be used together with Data Provider, which is responsible for actual data load 
 * from the database using specific filtering and sorting. Normally Data Provider must be in either session, or conversation
 * scope.
 */

public abstract class BaseExtendedDataModel<T extends BaseBean, T1 extends BaseDataProvider> extends SerializableDataModel {
	private int firstRow;
	private Integer currentPk;	
	private Map<Integer,T> wrappedData = new HashMap<Integer,T>();
	private List<Integer> wrappedKeys = null;
	private boolean detached = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1956179896877538628L;
	

	/**
	 * This method never called from framework.
	 * (non-Javadoc)
	 * @see org.ajax4jsf.model.ExtendedDataModel#getRowKey()
	 */
	@Override
	public Object getRowKey() {
		return currentPk;
	}
	/**
	 * This method normally called by Visitor before request Data Row.
	 */
	@Override
	public void setRowKey(Object key) {
		this.currentPk = (Integer) key;
		
	}
	

	/**
	 * This is main part of Visitor pattern. Method called by framework many times during request processing. 
	 */
	@Override
	public void walk(FacesContext context, DataVisitor visitor, Range range, Object argument) throws IOException {
		firstRow = ((SequenceRange)range).getFirstRow();
		int numberOfRows = ((SequenceRange)range).getRows();
		if (detached) { // Is this serialized model
// Here we just ignore current Rage and use whatever data was saved in serialized model. 
// Such approach uses much more getByPk() operations, instead of just one request by range.
// Concrete case may be different from that, so you can just load data from data provider by range.
// We are using wrappedKeys list only to preserve actual order of items.
			for (Integer key:wrappedKeys) {
				setRowKey(key);
				visitor.process(context, key, argument);
			}
		} else { // if not serialized, than we request data from data provider
			wrappedKeys = new ArrayList<Integer>();		
			
		  List<T> list = getDataProvider().getItemsByrange(new Integer(firstRow), numberOfRows, null, true);
		  for (int i = 0; i< list.size(); i ++) {			
		  //for (T item: list ) {
			   T item = list.get(i);
				wrappedKeys.add(item.getPK());
				wrappedData.put(item.getPK(), item);
				visitor.process(context, item.getPK(), argument);
			}
		
		}
	}
	/**
	 * This method must return actual data rows count from the Data Provider. It is used by pagination control
	 * to determine total number of data items.
	 */
	private Integer rowCount; // better to buffer row count locally
	@Override
	public int getRowCount() {		
		if (rowCount==null) {			
			rowCount = new Integer(getDataProvider().getRowCount());			
			return rowCount.intValue();
		} else {
			return rowCount.intValue();
		}
	}
	/**
	 * This is main way to obtain data row. It is intensively used by framework. 
	 * We strongly recommend use of local cache in that method. 
	 */
	@Override
	public Object getRowData() {
		if (currentPk==null) {
			return null;
		} else {
			T ret = wrappedData.get(currentPk);
			if (ret==null) {
				
				ret = (T)getDataProvider().getItemByPk(currentPk);
				
				wrappedData.put(currentPk, ret);
				return ret;
			} else {
				return ret;
			}
		}
	}

	/**
	 * Unused rudiment from old JSF staff.
	 */
	@Override
	public int getRowIndex() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unused rudiment from old JSF staff.
	 */
	@Override
	public Object getWrappedData() {
		return this.wrappedData;
		//throw new UnsupportedOperationException();
	}

	/**
	 * Never called by framework.
	 */
	@Override
	public boolean isRowAvailable() {
		if (currentPk==null) {
			return false;
		} else {			
			return getDataProvider().hasItemByPk(currentPk);			
		}
	}

	/**
	 * Unused rudiment from old JSF staff.
	 */
	@Override
	public void setRowIndex(int rowIndex) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unused rudiment from old JSF staff.
	 */
	@Override
	public void setWrappedData(Object data) {		
		throw new UnsupportedOperationException();
	}

	/**
	 * This method suppose to produce SerializableDataModel that will be serialized into View State and used on a post-back.
	 * In current implementation we just mark current model as serialized. In more complicated cases we may need to 
	 * transform data to actually serialized form.
	 */
	public  SerializableDataModel getSerializableModel(Range range) {
		if (wrappedKeys!=null) {
			detached = true;
// Some activity to detach persistent data from wrappedData map may be taken here.
// In that specific case we are doing nothing.
			return this; 
		} else {
			return null;
		}
	}
	/**
	 * This is helper method that is called by framework after model update. In must delegate actual database update to 
	 * Data Provider.
	 */
	@Override
	public void update() {
		getDataProvider().update();
	}

	public abstract T1 getDataProvider();
	
	public int getFirstRow() {
		return ++firstRow;
	}
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}	
	

}


