
Source : 
	https://medium.com/geekculture/system-design-elevator-system-design-interview-question-6e8d03ce1b44

Other Source To Read 
	https://leetcode.com/discuss/interview-question/object-oriented-design/124660/ood-design-elevator

This tutorial we will be dividing in 5 parts-
Discuss how we will be simulating the elevator design
	Identify the classes involved
	Elevator Design Code — Iteration-1
	Elevator Design Code — Iteration-2
	Elevator Design Code — Iteration-3
	Simulate the elevator design

We will be simulating the scenario-
	A person is on a particular floor. Suppose Ground Floor. He wants to go to 5th Floor. So he clicks on the elevator button with up direction.
	We will be calling this the ExternalRequest. This Request will be having the direction and the floor on which the button has been pressed 
			by the user i.e. source floor. The elevator will check the available requests if any and then process this request depending on some priority. 
			The elevator reaches the source floor i.e. the 0th or the ground floor. The person enters the elevator.
	The person enters the elevator. The person then presses the 5th floor button in the elevator to indicate the elevator to go to 5th floor.

This will be the internal request. So the internal request will be having only the floor to which the person wants to go to i.e. the destination floor. 
The elevator moves to the fifth floor. And the person then exits the elevator. If suppose when the elevator is moving from the ground floor to the 
fifth floor and it reaches the first floor. At this moment suppose another person on the second floor want’s to go in the UP direction. Then the 
elevator will stop for this request and the person on second floor will enter the elevator. Suppose he presses the 4th floor button. Then the elevator 
will first stop at 4th floor which was the destination of the person which entered on the second floor. Later the elevator stops on the fifth floor and 
the person from the ground floor exits. If suppose when the elevator is moving from the ground floor to the fifth floor and it reaches the first floor. 
At this moment suppose another person on the second floor want’s to go in the DOWN direction. Then the elevator will not stop for this request 
immediately.  Elevator will first go to the fifth floor where the person from the ground floor will exit. Elevator will then go to the second floor. 
The person will enter the elevator and press 0. The elevator will then move to the zeroth floor. 
Identify the classes involved 

Enum  Direction — This enum will have two values UP and DOWN.
Class ExternalRequest — The request made by the person from the floor when he requests for the elevator by pressing either the UP or the DOWN button. It will have the fields enum Direction and integer sourceFloor.
Class InternalRequest — The Request made by person when he enters the elevator. The person presses the floor number to which he wants to go. This will be the integer destinationFloor.
Class Request — This class will be the encapsulation for the ExternalRequest and InternalRequest. We will be passing this Request to the elevator to be processed. So this class will be having two fields — ExternalRequest and InternalRequest.
Enum State — This enum will have three values MOVING, STOPPED and IDLE.
Elevator — This class will represent the Elevator. It will have the fields currentFloor, currentState and currentDirection.
