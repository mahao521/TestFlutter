import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class SecondPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "second page",
        theme: ThemeData(primarySwatch: Colors.blue),
        home: SecondFulPage(
          title: "第二个界面",
        ));
  }
}

class SecondFulPage extends StatefulWidget {
  String title = "";

  SecondFulPage({Key, key, required this.title}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _SecondStatePage();
  }
}

class _SecondStatePage extends State<SecondFulPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: OutlinedButton(
          child: Text("第二个界面"),
          onPressed: () {
            setState(() {
              widget.title = "第二个界面 + 1";
            });
          },
        ),
      ),
    );
  }
}
