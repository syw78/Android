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

public class area2 extends AppCompatActivity {

    TextView textview;
    Document doc = null;
    LinearLayout layout = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area2);

        textview = (TextView) findViewById(R.id.textView1);


        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),mid.class);
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
        task.execute("http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=105");

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
                String s1 = "";
            int count = 0;
            count++;
                //data태그가 있는 노드를 찾아서 리스트 형태로 만들어서 반환
                NodeList nodeList = doc.getElementsByTagName("data");
                //data 태그를 가지는 노드를 찾음, 계층적인 노드 구조를 반환

                NodeList nodeList1 = doc.getElementsByTagName("location");
                for (int j = 0; j < nodeList1.getLength(); j++) {



                    //날씨 데이터를 추출
                    if (j % 1 == 0) {
                        s1 += "" + j/1 ;

                    }

                    Node node1 = nodeList1.item(j); //data엘리먼트 노드
                    Element fstElmnt1 = (Element) node1;

                    NodeList cityList = fstElmnt1.getElementsByTagName("city");
                    s1 += cityList.item(0).getChildNodes().item(0).getNodeValue() + ", ";

                }

                for (int i = 0; i < nodeList.getLength(); i++) {

                    //날씨 데이터를 추출
                    s += "" + " \n";


                    if (i % 13 == 0) {
                        s += "---------------------------------" + i/13 + "\n";

                    }


                    Node node = nodeList.item(i); //data엘리먼트 노드
                    Element fstElmnt = (Element) node;


                    NodeList timeList = fstElmnt.getElementsByTagName("tmEf");
                    s += timeList.item(0).getChildNodes().item(0).getNodeValue() + ", ";

                    NodeList LowList = fstElmnt.getElementsByTagName("tmn");
                    s += "최저 = " + LowList.item(0).getChildNodes().item(0).getNodeValue() + ", ";

                    NodeList highList = fstElmnt.getElementsByTagName("tmx");
                    //<wfKor>맑음</wfKor> =====> <wfKor> 태그의 첫번째 자식노드는 TextNode 이고 TextNode의 값은 맑음
                    s += "최고 = " + highList.item(0).getChildNodes().item(0).getNodeValue() + ", ";

                    NodeList websiteList = fstElmnt.getElementsByTagName("wf");
                    //<wfKor>맑음</wfKor> =====> <wfKor> 태그의 첫번째 자식노드는 TextNode 이고 TextNode의 값은 맑음
                    s += "날씨 = " + websiteList.item(0).getChildNodes().item(0).getNodeValue() + "\n" + "";


                }
                s1 += s + ":";

                textview.setText(s1);


                super.onPostExecute(doc);
            }

    }
}//end inner class - GetXMLTask


