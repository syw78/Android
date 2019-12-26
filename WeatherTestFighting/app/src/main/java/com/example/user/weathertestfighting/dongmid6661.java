package com.example.user.weathertestfighting;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class dongmid6661 extends AppCompatActivity {

    TextView textview;
    Document doc = null;
    LinearLayout layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dongmid6661);

        textview = (TextView) findViewById(R.id.textView1);


        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),dongmid661.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent,1000);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent,1000);
            }
        });
    }

    public void onClick(View view){
        GetXMLTask task = new GetXMLTask();
        task.execute("https://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=2650066000");

    }

    //private inner class extending AsyncTask
    private class GetXMLTask extends AsyncTask<String, Void, Document> {

        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            try {
                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder(); //XML문서 빌더 객체를 생성
                doc = db.parse(new InputSource(url.openStream())); //XML문서를 파싱한다.
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {

            String s = "";
            //data태그가 있는 노드를 찾아서 리스트 형태로 만들어서 반환
            NodeList nodeList = doc.getElementsByTagName("data");
            //data 태그를 가지는 노드를 찾음, 계층적인 노드 구조를 반환

            for(int i = 0; i< nodeList.getLength(); i++){

                //날씨 데이터를 추출
                s += ""+ " ";

                Node node = nodeList.item(i); //data엘리먼트 노드
                Element fstElmnt = (Element) node;


                NodeList dayList = fstElmnt.getElementsByTagName("day");
                s += dayList.item(0).getChildNodes().item(0).getNodeValue()+"일"+"  ";

                NodeList timeList = fstElmnt.getElementsByTagName("hour");
                s += timeList.item(0).getChildNodes().item(0).getNodeValue()+"시"+"  ";

                NodeList tempList = fstElmnt.getElementsByTagName("temp");
                s += tempList.item(0).getChildNodes().item(0).getNodeValue() +" , ";

                NodeList websiteList = fstElmnt.getElementsByTagName("wfKor");
                s += "날씨 = "+  websiteList.item(0).getChildNodes().item(0).getNodeValue() +"\n";

            }

            textview.setText(s);

            super.onPostExecute(doc);
        }

    }//end inner class - GetXMLTask
}

