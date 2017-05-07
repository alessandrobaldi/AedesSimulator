/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extrairmapa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author alessandromurtabaldi
 */
public class Extrairmapa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String leitura = null;

        try {
            // TODO code application logic here
            leitura = readFile("entrada.osm"); //inserir a entrada OSM aqui

        } catch (IOException ex) {
            Logger.getLogger(Extrairmapa.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] leituras = leitura.split("<tag k=\"building\"");

        List<Double> coordlat = new ArrayList<Double>();
        List<Double> coordlon = new ArrayList<Double>();
        int totalex = 0;
        for (int cont = 0; cont < leituras.length - 1; cont++) {
            try{
                if(leituras[cont].contains("restaurante"))
                    {
                        System.out.println("O restaurante Universitário está no nó "+ cont);
                    }
            String atual[] = leituras[cont].split("<way id=");
            String ref[] = atual[atual.length - 1].split("nd ref=\"");
            Double latmedia = 0.0;
            Double lonmedia = 0.0;
            int total = 0;

            for (int cont2 = 1; cont2 < ref.length; cont2++) {
                String referencia = ref[cont2].substring(0, ref[cont2].indexOf(">") - 2);

                String link = "https://www.openstreetmap.org/api/0.6/node/" + referencia;

                HttpClient client = HttpClientBuilder.create().build();
                HttpGet request = new HttpGet(link);
                String conteudo = null;
                try {
                    HttpResponse response = client.execute(request);
                    HttpEntity entity = response.getEntity();
                    conteudo = EntityUtils.toString(entity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (conteudo != null) {
                    totalex++;
                    System.out.println(totalex);
                    
                    String conteudomexer[] = conteudo.split("lon");
                    String latitude = conteudomexer[0].split("lat")[1];
                    latitude = latitude.substring(2, latitude.length() - 2);
                    String longitude = conteudomexer[1].substring(2, conteudomexer[1].indexOf("/") - 1);
                    double lat = Double.parseDouble(latitude);
                    double lon = Double.parseDouble(longitude);
                    latmedia = lat + latmedia;
                    lonmedia = lon + lonmedia;
                    total = total + 1;
                }
            }
            latmedia = latmedia / total;
            lonmedia = lonmedia / total;
            coordlat.add(latmedia);
            coordlon.add(lonmedia);
            }catch(Exception e)
            {
                
            }
        }

        //aqui inicia o cálculo para fazer os grafos no groove
        File arquivo = new File("saida.gsd"); //inserir saída GSD aqui
        try (FileWriter fw = new FileWriter(arquivo)) {
            fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                    + "<gxl xmlns=\"http://www.gupro.de/GXL/gxl-1.0.dtd\">\n"
                    + "    <graph role=\"graph\" edgeids=\"false\" edgemode=\"directed\" id=\"start\">\n"
                    + "        <attr name=\"$version\">\n"
                    + "            <string>curly</string>\n"
                    + "        </attr>\n");
            double latmax;
            double latmin;
            double lonmax;
            double lonmin;
            latmax = coordlat.get(0);
            latmin = coordlat.get(0);
            lonmax = coordlon.get(0);
            lonmin = coordlon.get(0);
            for (int cont = 0; cont < coordlat.size(); cont++) {
                if (latmax < coordlat.get(cont)) {
                    latmax = coordlat.get(cont);
                }
                if (latmin > coordlat.get(cont)) {
                    latmin = coordlat.get(cont);
                }
                if (lonmax < coordlon.get(cont)) {
                    lonmax = coordlon.get(cont);
                }
                if (lonmin > coordlon.get(cont)) {
                    lonmin = coordlon.get(cont);
                }
            }

            for (int cont = 0; cont < coordlat.size(); cont++) {
                double valor = mapDouble(coordlon.get(cont),lonmin,lonmax,0.0,coordlat.size()*100.0);
                int longitudegroove = (int) valor;
                double valor2 = mapDouble(coordlat.get(cont),latmin,latmax,0.0,coordlat.size()*100.0);
                int latitudegroove = (int) valor2;
                fw.write("        <node id=\"n");
                fw.write(String.valueOf(cont));
                fw.write("\">\n"
                        + "            <attr name=\"layout\">\n"
                        + "                <string>"+String.valueOf(longitudegroove)+" "+String.valueOf(latitudegroove)+" 71 32</string>\n"
                        + "            </attr>\n"
                        + "        </node>\n");
            }
             for (int cont = 0; cont < coordlat.size(); cont++) {
                 double valor = mapDouble(coordlon.get(cont),lonmin,lonmax,0.0,coordlat.size()*100.0);
                int longitudegroove = (int) valor;
                double valor2 = mapDouble(coordlat.get(cont),latmin,latmax,0.0,coordlat.size()*100.0);
                int latitudegroove = (int) valor2;
                fw.write("        <node id=\"n");
                fw.write(String.valueOf(cont+coordlat.size()));
              fw.write("\">\n"
                        + "            <attr name=\"layout\">\n"
                        + "                <string>"+String.valueOf(longitudegroove)+" "+String.valueOf(latitudegroove)+" 71 32</string>\n"
                        + "            </attr>\n"
                        + "        </node>\n");
            }
            
            for (int cont = 0; cont < coordlat.size(); cont++) {
                fw.write("        <edge from=\"n" + String.valueOf(cont)
                        + "\" to=\"n" + String.valueOf(cont)
                        + "\">\n");
                fw.write("            <attr name=\"label\">\n"
                        + "                <string>type:casa</string>\n"
                        + "            </attr>\n"
                        + "        </edge>\n");
            }
            for (int cont = coordlat.size(); cont < coordlat.size() * 2; cont++) {
                fw.write("        <edge from=\"n" + String.valueOf(cont)
                        + "\" to=\"n" + String.valueOf(cont)
                        + "\">\n");
                fw.write("            <attr name=\"label\">\n"
                        + "                <string>bool:true</string>\n"
                        + "            </attr>\n"
                        + "        </edge>\n");
            }
            for (int cont = 0; cont < coordlat.size(); cont++) {
                fw.write("        <edge from=\"n" + String.valueOf(cont)
                        + "\" to=\"n" + String.valueOf((cont + coordlat.size()))
                        + "\">\n");
                fw.write("            <attr name=\"label\">\n"
                        + "                <string>flag:foco</string>\n"
                        + "            </attr>\n"
                        + "        </edge>\n");
            }

            for (int cont = 0; cont < coordlat.size(); cont++) {
                for (int cont2 = cont + 1; cont2 < coordlat.size(); cont2++) {
                    double diflat = (coordlat.get(cont) * 10000) - (coordlat.get(cont2) * 10000);
                    double diflon = (coordlon.get(cont) * 10000) - (coordlon.get(cont2) * 10000);
                    diflat = diflat / 60;
                    diflon = diflon / 60;
                    diflat = diflat * 1852;
                    diflon = diflon * 1852;
                    double calculo = Math.sqrt(Math.pow(diflat, 2) + Math.pow(diflon, 2));
                    if (calculo < 100) {
                        fw.write("        <edge from=\"n" + String.valueOf(cont)
                                + "\" to=\"n" + String.valueOf(cont2)
                                + "\">\n");
                        fw.write("            <attr name=\"label\">\n"
                                + "                <string>voar</string>\n"
                                + "            </attr>\n"
                                + "        </edge>\n");
                        fw.write("        <edge from=\"n" + String.valueOf(cont2)
                                + "\" to=\"n" + String.valueOf(cont)
                                + "\">\n");
                        fw.write("            <attr name=\"label\">\n"
                                + "                <string>voar</string>\n"
                                + "            </attr>\n"
                                + "        </edge>\n");
                    }
                }
            }
            fw.write("    </graph>\n"
                    + "</gxl>");
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    public static double mapDouble(double x, double in_min, double in_max, double out_min, double out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}