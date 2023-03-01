class Strong {
  final double strengthLevel = 1500.99;
}

class QuickRunner {
  void runQuick() {
    print("runnnnnnnnn!");
  }
}

class Tall {
  final double height = 1.99;
}

class Human {
  final String name;
  Human({required this.name});
  void sayHello() {
    print("Hi my name is $name");
  }
}

enum Team { blud, red }

class Horse with Strong, QuickRunner {}

class Kid with QuickRunner {}

class Player extends Human with Strong, QuickRunner, Tall {
  final Team team;
  Player({
    required this.team,
    required String name,
  }) : super(name: name);

  @override
  void sayHello() {
    print("Hi my name is $name, and My team is $team");
  }
}

void main() {
  var player = Player(
    team: Team.red,
    name: 'kkk',
  );
  player.sayHello();
  Human human = player;
  human.sayHello();
  player.runQuick();
  player.runQuick();
}
