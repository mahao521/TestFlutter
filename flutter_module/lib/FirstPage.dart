import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class FirstPage extends StatefulWidget {
  FirstPage({Key? key, required String title}) : super(key: key);

  String title = "我是Flutter界面";

  final nativechannel = MethodChannel("com.example.testflutter/native");

  @override
  State<StatefulWidget> createState() {
    return _FirstPageState();
  }
}

class _FirstPageState extends State<FirstPage> {
  @override
  void initState() {
    super.initState();
    Future<dynamic> handler(MethodCall call) async {
      switch (call.method) {
        case "onActivityResult":
          setState(() {
            widget.title = call.arguments["message"];
          });
      }
    }

    widget.nativechannel.setMethodCallHandler(handler);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
        titleTextStyle: TextStyle(fontSize: 33,color: Colors.red),
      ),
      body: Align(
        alignment: Alignment.topCenter,
        child: Column(
          children: <Widget>[
            Text(widget.title,
                style: TextStyle(fontSize: 33, color: Colors.blue)),
            RawMaterialButton(
              onPressed: () {
                Map<String, dynamic> result = {'name': "从Flutter向Native界面传递参数"};
                widget.nativechannel.invokeListMethod('jumpNative', result);
              },
              child: Text("点击跳转到android"),
              fillColor: Colors.blue,
              textStyle: TextStyle(fontSize: 32, color: Colors.black),
            ),
          ],
        ),
      ),
    );
  }
}
