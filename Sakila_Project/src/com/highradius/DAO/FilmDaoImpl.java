package com.highradius.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.highradius.Action.DatabaseConnection;
import com.highradius.Action.Response;

public class FilmDaoImpl implements FilmDao {
	SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dateFormatter2 = new SimpleDateFormat("yyyy");

	@Override
	public HashMap<Integer, ArrayList<Response>> fetchData(int start, int limit, String title, String director,
			String releaseYear, String language) {
		
		int count = 0;
		ArrayList<Response> data=new ArrayList<Response>();
		HashMap<Integer, ArrayList<Response>> dataMap = new HashMap<>();
		Transaction tx = null ;
		Session session = null;
		try {
			System.out.println("Inside dataloadingIntoGrid function of FilmDaoImpl ");
			SessionFactory factory ;
			try {
		         factory = new Configuration().configure().buildSessionFactory();
		      } catch (Throwable ex) { 
		         System.err.println("Failed to create sessionFactory object." + ex);
		         throw new ExceptionInInitializerError(ex); 
		      }
			session = factory.openSession();
		    tx = session.beginTransaction();
		    if ((title != null && !title.trim().isEmpty()) || (releaseYear != null && !releaseYear.trim().isEmpty() && !releaseYear.equals("null")
					&& !releaseYear.equals("1970")) || (director != null && !director.trim().isEmpty()) || (language != null && !language.trim().isEmpty() && !language.equals("null"))) {
		    	Criteria cr = session.createCriteria(Response.class);
		    	if (title != null && !title.trim().isEmpty()) {
		    		cr.add(Restrictions.ilike("title",title+"%"));
				}
				if (releaseYear != null && !releaseYear.trim().isEmpty() && !releaseYear.equals("null")
						&& !releaseYear.equals("1970")) {
					cr.add(Restrictions.eq("releaseYear",releaseYear+"%"));
				}
				if (director != null && !director.trim().isEmpty()) {
					cr.add(Restrictions.ilike("description",director+"%"));
				}
				if (language != null && !language.trim().isEmpty() && !language.equals("null")) {
					cr.add(Restrictions.eq("languageId",language));
				}
				cr.add(Restrictions.eq("isDeleted",0));
				cr.setFirstResult(start);
				cr.setMaxResults(limit);
				data=(ArrayList<Response>) cr.list();
				count= Integer.parseInt(String.valueOf((Long)cr.setProjection(Projections.rowCount()).uniqueResult()));
		    }
		    else {
		    	data=(ArrayList<Response>) session.createQuery("select f.title, f.description, f.releaseYear, l.name,f.director,f.rating,f.specialFeatures, f.filmId from Response as f,Language as l where f.languageId=l.languageId AND f.isDeleted=0 ORDER BY f.filmId ASC").setFirstResult(start).setMaxResults(limit).list();
		    	count=Integer.parseInt(String.valueOf(((Long) session.createQuery("Select count(*) from Response as f WHERE f.isDeleted=0").uniqueResult())));
			}
		    System.out.println("Inside dataloadingIntoGrid list size"+data.size());
		    tx.commit();
		    dataMap.put(count, data);
		}catch(Exception e) {
	    	System.out.println("Unable to find Class Name in dataLoadingIntoGrid Method");
	    	if(tx!=null) {
	    		tx.rollback();
	    	}
		    e.printStackTrace();
		}finally {
			session.close();
		}
		return dataMap; 
	}

	public ArrayList<Response> fetchOtherData(ArrayList<Response> myData, String type) {
		
		PreparedStatement stmt = null;
		Set<String> special_features_set = new LinkedHashSet<>();
		Transaction tx = null ;
		Session session = null;
		try {
			SessionFactory factory ;
			try {
		         factory = new Configuration().configure().buildSessionFactory();
		      } catch (Throwable ex) { 
		         System.err.println("Failed to create sessionFactory object." + ex);
		         throw new ExceptionInInitializerError(ex); 
		      }
			session = factory.openSession();
		    tx = session.beginTransaction();
		    if (type.equals("language")) {
		    	myData=(ArrayList<Response>) session.createQuery("select l.languageId,l.name from Language as l ").list();
		    }else {
		    	Connection con = DatabaseConnection.initialize();
				if (type.equals("language")) {
					stmt = con.prepareStatement("select * from language");
				} else {
					stmt = con.prepareStatement("select * from film");
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Response tableResponse = new Response();
						String special_features = rs.getString("special_features");
						String[] sfarray = special_features.split(",");
						for (String a : sfarray) {
							special_features_set.add(a);
						}
					}
					for (String a : special_features_set) {
						Response tableResponse = new Response();
						tableResponse.setSpecialFeatures(a);
						myData.add(tableResponse);
					}
				}
			}
		    tx.commit();
		}catch(Exception e) {
	    	System.out.println("Unable to find Class Name in dataLoadingIntoGrid Method");
	    	if(tx!=null) {
	    		tx.rollback();
	    	}
		    e.printStackTrace();
		}finally {
			session.close();
		}
		return myData;
		
	}

	@Override
	public Response deleteData(String filmId) {
		
		Response successresponse = new Response();
		String[] words = filmId.split(",");
		ArrayList<Integer> queryids = new ArrayList<>();
		SessionFactory factory;
		try {
			factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		Session session = factory.openSession();
	    Transaction tx = null;
	    Query hqlQuery = null;
	    try {
	         tx = session.beginTransaction();
	         for (int i = 0; i < words.length; i++) {
				queryids.add(Integer.parseInt(words[i]));
			}
	         hqlQuery = session.createQuery("Update Response Set isDeleted = 1 where film_id IN (:filmIds)");
	         hqlQuery.setParameterList("filmIds", queryids);
	         System.out.println(filmId);
	         hqlQuery.executeUpdate();
	         tx.commit();
	         successresponse.setStatus(true);
	         successresponse.setMessage(words.length + " records deleted successfully");
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         successresponse.setStatus(false);
	         successresponse.setMessage("Failed to delete data");
	      } finally {
	         session.close(); 
	      }
	    return successresponse;	
	}

	public Response addData(Response filmData) {
		Response successresponse = new Response();
		SessionFactory factory;
		try {
			factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		Session session = factory.openSession();
	    Transaction tx = null;
	    try {
	         tx = session.beginTransaction();
	         session.save(filmData);
	         tx.commit();
	         successresponse.setStatus(true);
	         successresponse.setMessage("Data inserted successfully");
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         successresponse.setStatus(false);
	         successresponse.setMessage("Failed to insert data");
	      } finally {
	         session.close(); 
	      }
	    return successresponse;
	}
	
	public Response editData(Response filmData) {
		Response successresponse = new Response();
		SessionFactory factory;
		try {
			factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		Session session = factory.openSession();
	    Transaction tx = null;
	    try {
	         tx = session.beginTransaction();
	         session.update(filmData);
	         tx.commit();
	         successresponse.setStatus(true);
	         successresponse.setMessage("Data updated successfully");
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         successresponse.setStatus(false);
	         successresponse.setMessage("Failed to update data");
	      } finally {
	         session.close(); 
	      }
	    return successresponse;
	}

	
}
