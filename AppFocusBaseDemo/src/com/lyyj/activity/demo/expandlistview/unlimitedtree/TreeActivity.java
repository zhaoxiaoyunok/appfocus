package com.lyyj.activity.demo.expandlistview.unlimitedtree;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.appfocusbase.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
 

public class TreeActivity extends Activity implements OnItemClickListener{

	ListView code_list;
	private LinearLayout toolBar;
	TreeActivity oThis = this;
	String jStr="[{\"id\":\"53811254-021c-4edc-84f7-d4fe4abaf3fd\",\"text\":\"\u4E0A\u5B9E\u7269\u4E1A\",\"iconCls\":\"icon-company\",\"attributes\":{\"nodeType\":\"C\"},\"children\":[{\"id\":\"d8f3a362-171e-4eb9-979d-00d19897e815\",\"text\":\"\u4FDD\u7EFF\u90E8\",\"iconCls\":\"icon-deprat\",\"attributes\":{\"nodeType\":\"D\"},\"children\":[{\"id\":\"91e98336-3808-4365-bbe8-1437dd365fa5\",\"text\":\"\u5F20\u4E09\",\"iconCls\":\"icon-offline16\",\"attributes\":{\"nodeType\":\"U\",\"dpt\":\"d8f3a362-171e-4eb9-979d-00d19897e815\"},\"children\":[]},{\"id\":\"4272cd33-8e6b-4a12-8a13-1f8a0b20e3f1\",\"text\":\"\u674E\u56DB\",\"iconCls\":\"icon-offline16\",\"isOnline\":false,\"attributes\":{\"nodeType\":\"U\",\"dpt\":\"d8f3a362-171e-4eb9-979d-00d19897e815\"},\"children\":[]},{\"id\":\"873ec97d-476f-4059-ab65-7a90818f9b7f\",\"text\":\"\u738B\u4E94\",\"iconCls\":\"icon-offline16\",\"isOnline\":false,\"attributes\":{\"nodeType\":\"U\",\"dpt\":\"d8f3a362-171e-4eb9-979d-00d19897e815\"},\"children\":[]}]},{\"id\":\"9de04ae9-3210-4f4c-9cf9-56b6fbad977f\",\"text\":\"\u4FDD\u4FEE\u90E8\",\"iconCls\":\"icon-deprat\",\"isOnline\":null,\"attributes\":{\"nodeType\":\"D\"},\"children\":[{\"id\":\"278431a2-468b-4fc1-8e01-0048af5ce9fa\",\"text\":\"\u8D75\u516D\",\"iconCls\":\"icon-offline16\",\"isOnline\":false,\"attributes\":{\"nodeType\":\"U\",\"dpt\":\"9de04ae9-3210-4f4c-9cf9-56b6fbad977f\"},\"children\":[]},{\"id\":\"8a3af234-9bae-4d31-b4f6-2429db15cc8f\",\"text\":\"\u738B\u7426\",\"iconCls\":\"icon-offline16\",\"isOnline\":false,\"attributes\":{\"nodeType\":\"U\",\"dpt\":\"9de04ae9-3210-4f4c-9cf9-56b6fbad977f\"},\"children\":[]},{\"id\":\"26c758c4-1565-4101-b6e1-9609d47b05b6\",\"text\":\"\u5F20\u5DE5\",\"iconCls\":\"icon-offline16\",\"isOnline\":false,\"attributes\":{\"nodeType\":\"U\",\"dpt\":\"9de04ae9-3210-4f4c-9cf9-56b6fbad977f\"},\"children\":[]}]},{\"id\":\"82427257-650e-49a9-8fb8-8779afd43507\",\"text\":\"\u4FDD\u6D01\u90E8\",\"iconCls\":\"icon-deprat\",\"isOnline\":null,\"attributes\":{\"nodeType\":\"D\"},\"children\":[{\"id\":\"35915c02-8c73-44f1-b619-4eb840fcdc77\",\"text\":\"\u66F9\u683C\",\"iconCls\":\"icon-offline16\",\"isOnline\":false,\"attributes\":{\"nodeType\":\"U\",\"dpt\":\"82427257-650e-49a9-8fb8-8779afd43507\"},\"children\":[]},{\"id\":\"6d7c4372-8a20-4da0-87ad-f2cb9ddd9383\",\"text\":\"\u5B59\u5DE5\",\"iconCls\":\"icon-offline16\",\"isOnline\":false,\"attributes\":{\"nodeType\":\"U\",\"dpt\":\"82427257-650e-49a9-8fb8-8779afd43507\"},\"children\":[]}]},{\"id\":\"7b27ca2f-4f9b-4100-99aa-f41e97b70239\",\"text\":\"\u4FDD\u5B89\u90E8\",\"iconCls\":\"icon-deprat\",\"isOnline\":null,\"attributes\":{\"nodeType\":\"D\"},\"children\":[{\"id\":\"f8482080-358f-4543-9db4-8b70a1740502\",\"text\":\"\u5F90\u79C0\u4E3D\",\"iconCls\":\"icon-offline16\",\"isOnline\":false,\"attributes\":{\"nodeType\":\"U\",\"dpt\":\"7b27ca2f-4f9b-4100-99aa-f41e97b70239\"},\"children\":[]},{\"id\":\"a9d178b1-e505-472e-84cf-c72ba2398f9d\",\"text\":\"\u5F20\u5029\",\"iconCls\":\"icon-offline16\",\"isOnline\":false,\"attributes\":{\"nodeType\":\"U\",\"dpt\":\"7b27ca2f-4f9b-4100-99aa-f41e97b70239\"},\"children\":[]}]}]}]";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyyj_expandlv_unlimitedtree_main);

		toolBar = (LinearLayout) findViewById(R.id.toolBar);
		code_list = (ListView)findViewById(R.id.code_list);
		code_list.setOnItemClickListener(this);

		setToolBar(new String[]{ "选中结果","","","退出" },new int[]{0, 3});

		setPreson();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(this, "您选择的是:"+position, Toast.LENGTH_SHORT).show();

		// 这句话写在最后面
		((TreeAdapter)parent.getAdapter()).ExpandOrCollapse(position);
	}

	// 设置节点,可以通过循环或递归方式添加节点
	private void setPreson(){

		// 创建根节点
		Node root = new Node("合肥市公安局","000000");
		root.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_department);//设置图标
		root.setCheckBox(false);//设置节点前有无复选框
		//getList(list,root);//用于自定义
		
		// 创建1级子节点
		Node n1 = new Node("治安警察大队","1");
		n1.setParent(root);//设置父节点
		n1.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_department);

		Node n11 = new Node("李伟","13966664567");
		n11.setParent(n1);
		n11.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_police);
		Node n12 = new Node("张同刚","13966664567");
		n12.setParent(n1);
		n12.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_police);

		n1.add(n11);
		n1.add(n12);


		// 创建1级子节点
		Node n2 = new Node("刑事警察大队","2");
		n2.setParent(root);
		n2.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_department);
		Node n21 = new Node("曹梦华","13966664567");
		n21.setParent(n2);
		n21.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_police);
		Node n22 = new Node("文燕","13966664567");
		n22.setParent(n2);
		n22.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_police);
		//创建2级子节点
		Node n221 = new Node("基友","15890875672");
		n221.setParent(n22);
		n221.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_police);

		Node n222 = new Node("爸爸","15890875672");
		n222.setParent(n22);
		n222.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_police);

		Node n223 = new Node("妈妈","15890875672");
		n223.setParent(n22);
		n223.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_police);

		n22.add(n221);
		n22.add(n222);
		n22.add(n223);

		Node n23 = new Node("赵文涛","13766604867");
		n23.setParent(n2);
		n23.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_police);
		n2.add(n21);
		n2.add(n22);
		n2.add(n23);


		// 创建1级子节点
		Node n3 = new Node("巡警防暴大队","3");
		n3.setParent(root);
		n3.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_department);
		Node n31 = new Node("崔逊田","15305510131");
		n31.setParent(n3);
		n31.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_police);
		Node n32 = new Node("测试用户","13855196982");
		n32.setParent(n3);
		n32.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_police);

		//创建2级子节点
		Node n33 = new Node("巡警第一中队","31");
		n33.setParent(n3);
		n33.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_department);

		Node n331 = new Node("张楠","15890875672");
		n331.setParent(n33);
		//n331.setIcon(R.drawable.icon_police);
		Node n332 = new Node("阮明东","15890875672");
		n332.setParent(n33);
		n332.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_police);
		Node n333 = new Node("司徒正雄","15890875672");
		n333.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_police);
		n333.setParent(n33);

		//创建3级子节点
		Node n3311 = new Node("儿子","15890875672");
		n3311.setParent(n331);
		n3311.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_police);

		Node n3312 = new Node("女儿","15890875672");
		n3312.setParent(n331);
		n3312.setIcon(R.drawable.lyyj_expandlv_unlimitedtree_icon_police);

		Node n3313 = new Node("老婆","15890875672");
		n3313.setParent(n331);

		n331.add(n3311);
		n331.add(n3312);
		n331.add(n3313);
		//n333.setIcon(R.drawable.icon_police);

		n33.add(n331);
		n33.add(n332);
		n33.add(n333);

		n3.add(n31);
		n3.add(n32);
		n3.add(n33);

		root.add(n3);
		root.add(n1);
		root.add(n2);


		TreeAdapter ta = new TreeAdapter(oThis,root);
		// 设置整个树是否显示复选框
		ta.setCheckBox(true);
		// 设置展开和折叠时图标
		ta.setExpandedCollapsedIcon(R.drawable.lyyj_expandlv_unlimitedtree_tree_ex, R.drawable.lyyj_expandlv_unlimitedtree_tree_ec);
		// 设置默认展开级别
		ta.setExpandLevel(2);
		code_list.setAdapter(ta);
	} 

	// 设置底部工具栏
	private void setToolBar(String[] name_array,int[] pos_array){
		toolBar.removeAllViews();

		GridView toolbarGrid = new GridView(this);
		toolbarGrid.setNumColumns(4);// 设置每行列数
		toolbarGrid.setGravity(Gravity.CENTER);// 位置居中
		ToolbarAdapter adapter = new ToolbarAdapter(this,name_array,null,pos_array);
		toolbarGrid.setAdapter(adapter);
		toolbarGrid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				switch (position) {
				case 0://显示选中结果
					List<Node> nodes = ((TreeAdapter)code_list.getAdapter()).getSeletedNodes();
					String msg = "";

					for(int i=0;i<nodes.size();i++){
						Node n = nodes.get(i);
						if(n.isLeaf()){
							if(!msg.equals(""))msg+=",";
							msg += n.getText()+"("+n.getValue()+")";
						}

					}
					if(msg.equals("")){
						Toast.makeText(oThis, "没有选中任何项", Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(oThis, msg, Toast.LENGTH_SHORT).show();
					}

					break;
				case 3://返回
					oThis.finish();
					System.exit(0);
					break;
				}
			}
		});
		toolBar.addView(toolbarGrid);
	}

	public void getList(List<Node> listName,Node root){
		for(Node node:listName){
			Node n11 = new Node(node.getText(),node.getValue());
			n11.setParent(root);
			root.add(n11);
			if(node.getChildren().size()>0){
				getList(node.getChildren(),n11);
			}
		}
	}
}