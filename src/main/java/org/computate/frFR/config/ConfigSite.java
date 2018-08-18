package org.computate.frFR.config;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**	Charge les propriétés dans le fichier de configuration de l'application dans des champs spécifiques.
 */
public class ConfigSite extends Object {

	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	;
	protected void _appliNom() {
		appliNom = System.getenv("appliNom"); 
	}

	protected void _appliChemin() {
		appliChemin = System.getenv("appliChemin"); 
	}

	protected void _cheminSrcMainJava() {
		cheminSrcMainJava = appliChemin + "/src/main/java";
	}

	protected void _cheminSrcGenJava() {
		cheminSrcGenJava = appliChemin + "/src/gen/java";
	}

	protected void _cheminConfiguration() {
		cheminConfiguration = appliChemin + "/config/" + appliNom + ".config";
		System.out.println("cheminConfiguration: " + cheminConfiguration);  
	}

	protected void _fichierConfiguration() {
		fichierConfiguration = new File(cheminConfiguration);
	}

	protected void _configurations() {
		configurations = new Configurations();
	}

	protected void _config() {
		config = configurations.ini(fichierConfiguration);
	}

	protected void _langueNom() {
		langueNom = config.getString(appliNom + ".langueNom");
	}

	protected void _langueNomActuel() {
		if(StringUtils.equals(langueNom, "tout"))
			langueNomActuel = "frFR";
		else
			langueNomActuel = langueNom;
	}

	protected void _toutesLangues() {
		toutesLangues = config.getStringArray(appliNom + ".toutesLangues");
	}

	protected void _autresLangues() {
		autresLangues = ArrayUtils.removeElement(toutesLangues, langueNom);
	}

	protected void _langueIndexe() {
		langueIndexe = ArrayUtils.contains(toutesLangues, langueNom);
	}

	protected void _nomDomaine() {
		nomDomaine = config.getString(appliNom + ".nomDomaine");
	}

	protected void _nomEnsembleDomaine() {
		String[] partis = StringUtils.split(nomDomaine, ".");
		ArrayUtils.reverse(partis);
		nomEnsembleDomaine = StringUtils.join(partis, ".");
	}

	protected void _nomFicherConfig() {
		nomFichierConfig = config.getString(appliNom + ".nomFichierConfig", appliNom + ".config");
	}

	protected void _cheminConfig() {
		cheminConfig = config.getString(appliNom + ".cheminConfig", appliChemin + "/config/" + nomFichierConfig);
	}

	protected void _versionMaven() {
		versionMaven = config.getString("maven.versionMaven", "3.5.3");
	}

	protected void _versionZookeeper() {
		versionZookeeper = config.getString("maven.versionZookeeper", "3.5.4");
	}

	protected void _prefixePortZookeeper() {
		prefixePortZookeeper = config.getString("zookeeper.prefixePortZookeeper", "102");
	}

	protected void _portClientZookeeper() {
		portClientZookeeper = config.getString("zookeeper.portClientZookeeper", prefixePortZookeeper + "81");
	}

	protected void _portAdminZookeeper() {
		portAdminZookeeper = config.getString("zookeeper.portAdminZookeeper", prefixePortZookeeper + "80");
	}

	protected void _versionSolr() {
		versionSolr = config.getString("zookeeper.versionSolr", "7.3.1");
	}

	protected void _prefixePortSolr() {
		prefixePortSolr = config.getString("zookeeper.prefixePortSolr", "103");
	}

	protected void _portSolr() {
		portSolr = config.getString("zookeeper.portSolr", prefixePortSolr + "83");
	}

	protected void _urlSolrComputate() {
		urlSolrComputate = config.getString("zookeeper.urlSolr", "http://localhost:" + portSolr + "/solr/computate");
	}

	protected void _clientSolrComputate() {
		clientSolrComputate = new HttpSolrClient.Builder(urlSolrComputate).build();
	}

	protected void _cheminsARegarder() {
		cheminsARegarder.add(cheminSrcMainJava);
	}

	protected void _cheminsSource() {
		cheminsSource.add(cheminSrcMainJava);
		cheminsSource.add(cheminSrcGenJava);
	}

	protected void _toutCheminsSource() {
		toutCheminsSource.add(cheminSrcMainJava);
		toutCheminsSource.add(cheminSrcGenJava);
	}

	protected void _nomsMethodeTest() {
	}

	public void initConfigSite() {
		_appliNom();
		_appliChemin();
		_cheminSrcMainJava();
		_cheminSrcGenJava();
		_cheminConfiguration();
		_fichierConfiguration();
		_configurations();
		_config();
		_langueNom();
		_langueNomActuel();
		_toutesLangues();
		_autresLangues();
		_langueIndexe();
		_nomDomaine();
		_nomEnsembleDomaine();
		_nomFicherConfig();
		_cheminConfig();
		_versionMaven();
		_versionZookeeper();
		_prefixePortZookeeper();
		_portClientZookeeper();
		_portAdminZookeeper();
		_versionSolr();
		_prefixePortSolr();
		_portSolr();
		_urlSolrComputate();
		_clientSolrComputate();
		_cheminsARegarder();
		_cheminsSource();
		_toutCheminsSource();
		_nomsMethodeTest();
	}

	public null regex() {
		String o = regex(motif, texte, 1);
		return o;
	}

	public null regex() {
		String o = regex(motif, texte, 1);
		if(StringUtils.isEmpty(o))
			return valeurDefaut;
		else
			return o;
	}

	public null regex() {
		String o = null;
		if(motif != null && texte != null) {
			Matcher m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
			boolean trouve = m.find();
			if(trouve)
				o = m.group(groupe);
		}
		return o;
	}

	public null regexListe() {
		ArrayList<String> resultats = new ArrayList<String>();
		String o = null;
		if(motif != null && texte != null) {
			Matcher m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
			boolean trouve = m.find();
			while(trouve) {
				o = m.group(1);
				resultats.add(o);
				trouve = m.find();
			}
		}
		return resultats;
	}

	public null regexRemplacerTout() {
		String codeSourceLangue = codeSource;
		if(!StringUtils.isEmpty(commentaire)) {
			Matcher m = Pattern.compile("^r." + langueNom + ": (.*\\n.*)", Pattern.MULTILINE).matcher(commentaire);
			boolean trouve = m.find();
			
			while(trouve) {
				String texteRechercheRemplacement = m.group(1);
				String[] partisRechercheRemplacement = StringUtils.split(texteRechercheRemplacement, "\n");
				if(partisRechercheRemplacement.length == 2) {
					String texteRecherche = partisRechercheRemplacement[0];
					String texteRemplacement = partisRechercheRemplacement[1];

					Matcher m2 = Pattern.compile(Pattern.quote(texteRecherche), Pattern.MULTILINE).matcher(codeSourceLangue);
					boolean trouve2 = m2.find();
					StringBuffer sortie2 = new StringBuffer();
					
					while(trouve2) {
						m2.appendReplacement(sortie2, texteRemplacement);
						trouve2 = m2.find();
					}
					m2.appendTail(sortie2);
					codeSourceLangue = sortie2.toString();
				}

				trouve = m.find();
			}
		}
		return codeSourceLangue;
	}

	public null concat() { 
		String resultat = Stream.of(valeurs).collect(Collectors.joining());
		return resultat;
	}

}
