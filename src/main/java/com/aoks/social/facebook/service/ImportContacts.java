//package com.aoks.social.facebook.service;
//
//import javax.enterprise.context.SessionScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.aoks.enterprise.control.bean.IndividualPartnerBean;
//import com.aoks.enterprise.control.factory.IndividualPartnerFactory;
//import com.aoks.enterprise.model.entities.IndividualPartner;
//import com.aoks.enterprise.service.IndividualPartnerManager;
//import com.aoks.register.control.factory.IndividualRegisterFactory;
//import com.aoks.social.facebook.config.Connect;
//import com.aoks.utils.webmvc.AbstractController;
//import com.aoks.utils.webmvc.AbstractManager;
//import com.aoks.utils.webmvc.GenericDataModel;
//import com.aoks.utils.webmvc.GenericFactory;
//import com.aoks.utils.webmvc.PersistenceWrapper;
//import com.restfb.Connection;
//import com.restfb.types.User;
//
//@Named("import")
//@SessionScoped
//public class ImportContacts extends AbstractController<IndividualPartner, IndividualPartnerBean>{
//
//	private static final long serialVersionUID = 1L;
//	
//	@Inject private Connect connect;
//	@Inject private FacebookManager facebookManager;
//	@Inject private IndividualRegisterFactory individualFactory;
//	@Inject private IndividualPartnerManager manager;
//	@Inject private IndividualPartnerFactory factory;
//	@Inject private PersistenceWrapper wrapper;
//	
//	private static final Logger logger = LoggerFactory.getLogger(PersistenceWrapper.class);
//	
//	public void build(){
////		Connection<User> connectionUser = facebookManager.loadFriendList(connect.getFacebookClient());
////		System.out.println(connectionUser.getData().size());
////		for(User user : connectionUser.getData()){
////			
////			logger.debug("Executing import with query '{}'", user.getName());
////			
////			bean = new IndividualPartnerBean().load(user);
////			wrapper.saveOrUpdate(bean.build(factory));
////		}
//	}
//
//
//	public Connect getConnect() 							{ return connect; }
//	public FacebookManager getFacebookManager() 			{ return facebookManager; }
//	public IndividualRegisterFactory getIndividualFactory() { return individualFactory; }
//
//
//	@Override public Class<IndividualPartnerBean> getBeanClazz() 			{ return IndividualPartnerBean.class; }
////	@Override public AbstractManager<IndividualPartner> getManager() 		{ return manager; }
////	@Override public GenericFactory<IndividualPartner> getFactory() 		{ return factory; }
//
//
//	@Override
//	public GenericDataModel<IndividualPartnerBean> getDataModel() {
//		return null;
//	}
//	
//	
//}
