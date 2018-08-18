package org.computate.frFR.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrDocument;
import org.computate.frFR.chaine.Chaine;
import org.computate.frFR.config.ConfigSite;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaType;

public class RegarderClasseBase extends ConfigSite {

	;
	;
	;
	;
	;
	protected void _appliChemin() {
		appliChemin = args[0]; 
	}

	protected void _classeCheminAbsolu() {
		classeCheminAbsolu = args[1];
	}

	protected void _appliChemins() {
		for(String langueNom : autresLangues) {  
			String appliCheminLangue = config.getString(appliNom + ".appliChemin_" + langueNom); 
			System.out.println("appliCheminLangue " + langueNom + ": " + appliCheminLangue);
			if(StringUtils.isEmpty(appliCheminLangue)) {
				appliChemins.put(langueNom, appliChemin);
			}
			else {
				appliChemins.put(langueNom, appliCheminLangue);
			}
		}
	}

	protected void _classeDocs() {
	}

	protected void _bricoleur() {
		bricoleur = new JavaProjectBuilder();
		for(String cheminSource : toutCheminsSource) {
			File répertoireSource = new File(cheminSource);
			bricoleur.addSourceFolder(répertoireSource);
		}
	}

	public void initRegarderClasseBase() {
		initConfigSite();
		_classeCheminAbsolu();
		_appliChemins();
		_classeDocs();
		_bricoleur();
	}

	protected null etendClasse() {
//		for(JavaClass classeSuperQdox : classesSuperQdox) {
//			if(classeSuperQdox.getCanonicalName().equals(nomCanonique))
//				return true;
//		}
		boolean resultat = etendClasse(nomCanonique, classeQdox);
		return resultat;
	}

	protected null etendClasse() {
		JavaClass classeQdox = bricoleur.getClassByName(nomCanoniqueActuel);
		return etendClasse(nomCanoniqueRecherche, classeQdox);
	}

	protected null etendClasse() {
		if(nomCanonique != null && classeQdox != null) {
			if(classeQdox.getCanonicalName().equals(Object.class.getCanonicalName()))
				return false;
			if(classeQdox.getCanonicalName().equals(nomCanonique))
				return true;
			else if(!classeQdox.equals(classeQdox.getSuperClass()))
				return etendClasse(nomCanonique, classeQdox.getSuperJavaClass());
		}
		return false;
	}

	public null contientChamp() {
		JavaClass classeQdox = bricoleur.getClassByName(c.getCanonicalName());
		Boolean o = contientChamp(classesSuperQdoxEtMoi, nomChamp, classeQdox);
		return o;
	}

	public null contientChamp() {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		for(JavaClass classeSuper : classesSuperQdoxEtMoi) {
			JavaField champRequeteSite = classeSuper.getFieldByName(nomChamp);
			JavaMethod methodeRequeteSite = classeSuper.getMethod("_" + nomChamp, listeParams, false);
			Boolean o = champRequeteSite != null || methodeRequeteSite != null;
			if(o)
				return true;
		}
		return false;
	}

	public null contientMethodeSeul() {
		JavaMethod o = obtenirMethodeSeul(classeQdox, nomMethode, tableauParams);
		return o != null;
	}

	public null contientMethode() {
		JavaMethod o = obtenirMethode(classeQdox, nomMethode, tableauParams);
		return o != null;
	}

	public null contientAttribut() {
		Boolean resultat = false;
		if(classeAttribut.getCanonicalName().startsWith(nomEnsembleDomaine.toString())) {
			JavaField attribut = classeAttribut.getFieldByName(nomAttribut);
			if(attribut == null) {
				resultat = contientAttribut(nomEnsembleDomaine, nomAttribut, classeAttribut.getSuperJavaClass());
			}
			else {
				resultat = true;
			}
		}
		else {
			resultat = false;
		}
		return resultat;
	}

	public null obtenirMethode() {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		JavaMethod methode = classeQdox.getMethodBySignature(nomMethode, listeParams, true);
		return methode;
	}

	public null obtenirMethode() {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		for(JavaClass classeSuper : classesSuperQdoxEtMoi) {
			JavaMethod methode = classeSuper.getMethodBySignature(nomMethode, listeParams);
			if(methode != null)
				return methode;
		}
		return null;
	}

	public null obtenirMethodeSeul() {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		JavaMethod methode = classeQdox.getMethodBySignature(nomMethode, listeParams, false);
		return methode;
	}

}
