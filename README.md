# jPlanningPoker
A tool to help with Scrum Planning poker sessions.

## Compile and run
Just use the maven install command. The core module will produce a war file which can be deployed on a Servlet 3.0 compatible Java application server.
The standalone module will produce an executable jar with an embedded Tomcat7 server. When this jar is put in the same directory as the war file it will
run Tomcat in embedded mode and deploy the application in the root.

The standalone jar accepts a single argument, the port number. If this argument is not numeric, or not supplied, port 8080 will be used.

## How to use
The point of the tool is to be used during Planning poker sessions. It is best to connect the host to a beamer. Using the QR Code, mobile devices can easily join
the poker session (resizing the dialog will also resize the QR code). After someone has joined he can fill in a name and the host will show him in the list. A red cell means that nothing was pokered yet.

After the time to poker has ended, the host can click 'Show cards' to show everybody what card was drawn by who. After the discussion, the host can click reset
to remove all the cards and go for another poker round.