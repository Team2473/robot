; (function ()
{
  function SV() {

    // A collection of the GUI elements
    this.port      = document.getElementById('port-selection');
    this.connect   = document.getElementById('port-connect');


    this.ycenterRange = document.getElementById('ycenterRange');
    this.clearButton = document.getElementById('clearButton');
    this.startSweep = document.getElementById('startSweep');
    this.rotationSpeed = document.getElementById('rotationSpeed');
    this.speedValue = document.getElementById('speedValue');

    this.xcenterRange = document.getElementById('xcenterRange');
    this.idnumber      = document.getElementById('idnumber');
    this.sendPacket      = document.getElementById('sendPacket');

    this.instructionSelection      = document.getElementById('instructionSelection');

    this.d1 = document.getElementById('d1');
    this.d2 = document.getElementById('d2');
    this.d3 = document.getElementById('d3');
    this.d4 = document.getElementById('d4');
    this.button = document.getElementById('button');
    this.instruction = document.getElementById('instruction');

    this.oldInstruction = 'inst0';

    // Stats variables
    this.updatingConnection = false;
    this.connection         = null;
    this.connected = false;

    this.transmit_buffer = new Uint8Array(10);

    // Start up functions
    this.updatePorts();
    this.attachEvents();
    //this.myTimer();

  }

  // Ensures the constructor is set correctly
  SV.prototype.constructor = SV;

  ////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////

  SV.ScannerConnection =
  {
    bitrate:    115200,
    dataBits:   "eight",
    parityBit:  "no",
    stopBits:   "one"
  };

  ////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////

  SV.prototype.updatePorts = function()
  {

    // A reference to the SV object for use in callbacks
    var self = this;
	  getDevices = chrome.serial.getDevices || chrome.serial.getPorts;

    // Ask Chrome to find any serial devices
    getDevices(function (ports) {

      // Go through each device
      ports.forEach(function (port) {

        // Create a new option element
        var option = document.createElement('option');

        // Set the elements properties
        option.innerHTML = port.path;
        option.value = port.path;

        // Add it to the select box
        self.port.appendChild(option);

      }); // END forEach

    }); // END getDevices

  };

//   SV.prototype.myTimer = function()
//   {
//
//      var self = this;
//
//      var d = new Date();
//
//      document.getElementById("hertbeatbeat").value = d.toLocaleTimeString();
//      document.getElementById("dec1").innerHTML = d.toLocaleTimeString();
//
//   };

  SV.prototype.sendCommanderPacket =  function(cmdPort, cmdOption)
  {
//	document.getElementById("packet").innerHTML= "rawr" ;

    this.transmit_buffer[0] = cmdPort.charCodeAt(0);
    this.transmit_buffer[1] = cmdOption.charCodeAt(0);
    this.transmit_buffer[2] = '\n'.charCodeAt(0);
//     this.transmit_buffer[3] =  parseInt((tilt >> 8)& 0xff);
//     this.transmit_buffer[4] = parseInt(tilt & 0xff);
//     this.transmit_buffer[5] = parseInt(button & 0xff);
//     this.transmit_buffer[6] = parseInt(ext);
//     this.transmit_buffer[7] = 255-(( this.transmit_buffer[1] +  this.transmit_buffer[2] +  this.transmit_buffer[3] +  this.transmit_buffer[4] + this.transmit_buffer[5] + this.transmit_buffer[6] )%256);

 	 this.transmit(this.transmit_buffer);

  };

    SV.prototype.sendWriteValuePacket =  function(cmdPort, cmdOption, cmdValue1, cmdValue2)
  {
//  document.getElementById("packet").innerHTML= "rawr" ;

  this.transmit_buffer[0] = cmdPort.charCodeAt(0);
  this.transmit_buffer[1] = cmdOption.charCodeAt(0);
  this.transmit_buffer[2] = cmdValue1.charCodeAt(0);
  this.transmit_buffer[3] = cmdValue2.charCodeAt(0);
  this.transmit_buffer[4] = '\n'.charCodeAt(0);
//     this.transmit_buffer[3] =  parseInt((tilt >> 8)& 0xff);
//     this.transmit_buffer[4] = parseInt(tilt & 0xff);
//     this.transmit_buffer[5] = parseInt(button & 0xff);
//     this.transmit_buffer[6] = parseInt(ext);
//     this.transmit_buffer[7] = 255-(( this.transmit_buffer[1] +  this.transmit_buffer[2] +  this.transmit_buffer[3] +  this.transmit_buffer[4] + this.transmit_buffer[5] + this.transmit_buffer[6] )%256);

   this.transmit(this.transmit_buffer);

  };


  SV.prototype.attachEvents = function()
  {

    // A reference to the SV object for use in callbacks
    var self = this;

    var buffer = new Uint8Array(9);

    // Connects to the selected port
    self.connect.addEventListener('click', function ()
    {
      self.updateConnection();
    });


   	  canvasHeight = window.innerHeight;
      canvasWidth = window.innerWidth-240;

      centerY = canvasHeight/2;
      centerX = canvasWidth/2;
      document.getElementById("ycenterRange").value =centerY;
      document.getElementById("xcenterRange").value =centerX;

   	redrawCanvas();


    self.ycenterRange.addEventListener('input', function ()
    {
    	//console.log(document.getElementById('ycenterRange').value);
    	centerY = parseInt(document.getElementById('ycenterRange').value);


		ctx.fillStyle="#FFFFFF";
		ctx.fillRect(0,0,canvasWidth, canvasHeight);


		draw();


    });


    self.xcenterRange.addEventListener('input', function ()
    {
    	centerX = parseInt(document.getElementById('xcenterRange').value);


		ctx.fillStyle="#FFFFFF";
		ctx.fillRect(0,0,canvasWidth, canvasHeight);



		draw();


    });

    self.clearButton.addEventListener('click', function ()
    {

		console.log("clear");

		draw();


    });


    self.startSweep.addEventListener('click', function ()
    {

    	if(scanning == 0)
    	{
    		self.startSweep.innerHTML = "Stop Scanning";
			  self.sendCommanderPacket('S', 'D');
			  scanning = 1;
    	}
    	else
    	{
    		self.startSweep.innerHTML = "Start Scanning";
			  self.sendCommanderPacket('S', 'X');
			  scanning = 0;

    	}

    });

    self.rotationSpeed.addEventListener('change', function ()
    {

    	//send '3' extended instruction to set speed value from pan bytes
		//console.log("test rotationSpeed1");
		//self.sendCommanderPacket(parseInt(self.rotationSpeed.value),0,0,3);
		//console.log("test rotationSpeed2");
    self.sendWriteValuePacket('M', 'S', '0', rotationSpeed.value);

		console.log(self.rotationSpeed.value);

		document.getElementById("speedValue").value = rotationSpeed.value;


    });

    self.speedValue.addEventListener('change', function ()
    {

    	//send '3' extended instruction to set speed value from pan bytes
		//console.log("test speedValue1");
		//self.sendCommanderPacket(parseInt(self.speedValue.value),0,0,3);
		//console.log("test speedValue2");
    self.sendWriteValuePacket('M', 'S', '0', speedValue.value);

		console.log(self.speedValue.value);

		document.getElementById("rotationSpeed").value = speedValue.value;


    });


    var iS = document.getElementById('instructionSelection');
    if(iS)
    {
		self.instructionSelection.addEventListener('change', function ()
		{
			//clear space
			var list = document.getElementsByClassName("inst");
			for (var i = 0; i < list.length; i++) {
				 list[i].style.display = "none";
			}

			//show intended part.
			document.getElementById(document.getElementById('instructionSelection').value).style.display = "block";

		});

  	};
}

  SV.prototype.updateConnection = function() {

    // A reference to the SV object for use in callbacks
    var self = this;

    // Prevent the function from firing more than once
    if (self.updatingConnection) {
      return;
    }

    // Lock the function
    self.updatingConnection = true;

    // If a connection isn't made, then make one.
    if (!self.connection) {
   		self.connected = true;
      // Update the status text
      self.connect.classList.add('disabled');
      self.connect.innerHTML = 'Connecting...';

      // Ask chrome to create a connection
      chrome.serial.connect(self.port.value, SV.ScannerConnection, function (info) {

      // Store the connection
      self.connection = info;

      //chrome.serial.onReceive.addListener(onReceiveCallback);

      // Unlock the function
      self.updatingConnection = false;

      // Update the status text
      self.connect.classList.remove('disabled');
      self.connect.innerHTML = 'Disconnect';

		  //ctx.fillStyle="#FFFFFF";
		  //ctx.fillRect(0,0,canvasWidth, canvasHeight);

		  draw();

 		  chrome.serial.onReceive.addListener(readHandler);

  		//document.getElementById("datadata").innerHTML = "add listener";

      //Read motor speed value
      //self.sendCommanderPacket(0x65);

  console.log( "ADDL:" );
      });

    // If there is already a connection, destroy it.
    } else {

		self.connected = false;
      // Update the status text
      self.connect.classList.add('disabled');
      self.connect.innerHTML = 'Disconnecting...';


      // Ask Chrome to close the connection
      chrome.serial.disconnect(self.connection.connectionId, function (result) {

        // Clear the stored connection information
        self.connection = null;

        // Unlock the function
        self.updatingConnection = false;

        // Update the status text
        self.connect.classList.remove('disabled');
        self.connect.innerHTML = 'Connect';

      });

    }


  };

  SV.prototype.transmit = function(buffer) {

    // A reference to the SV object for use in callbacks
    var self = this;


   //  // Chrome's serial API expects data to be sent as a data buffer.

   //  // Sets the data in the buffer
   // // buffer[0] = action ? 1 : 0;

   //  var buffer = new Uint8Array(17);
 		// buffer[0] = 0xff;
 		// buffer[1] = 0x00;
 		// buffer[2] = 0x00;
 		// buffer[3] = 0x00;
 		// buffer[4] = 0x00;
 		// buffer[5] = 0x00;
 		// buffer[6] = 0x00;
 		// buffer[7] = 0x00;
 		// buffer[8] = 0x00;
 		// buffer[9] = 0x00;
 		// buffer[10] = 0x00;
 		// buffer[11] = 0x00;
 		// buffer[12] = 0x00;
 		// buffer[13] = 0x00;
 		// buffer[14] = 0x00;
 		// buffer[15] = 0x40;
 		// buffer[16] = 0xBF;

    // Transmit the data to the Scanner
    chrome.serial.send(self.connection.connectionId, buffer.buffer, function (sendInfo) {});

  };

  document.addEventListener('DOMContentLoaded', function () {

    // Start the SV class
    window.SV = new SV();

  });

})();
//Keep in mind that processing the buffer may lag behind the buffer itself, so consloe.logs might be out of sync between what you see in the buffer and what's been processed
var state = {
  waitForCMD1: 1,
  waitForCMD2: 2,
  waitForParam: 3,
  waitForStatus1: 4,
  waitForStatus2: 5,
  waitForSumByte: 6,
  waitForTerm1: 7,
  waitForTerm2: 8
}

var connectionIdExt;
var responseScanPacket = new Uint8Array(8);
var responseScanPacketIndex = -1;
var responsePacket = new Uint8Array(20);
var lastByteTime;
var newPacket = 0;
var rxState = state.waitForCMD1;
var tempSum = 0;
//there's a big here  - responseScanPacketIndex ios getting bigger than 6( its 12) probably a timing bug

var serialBuffer = [0];
var serialIndex = -1;
var packetsReceived = 0;

var rp = {
    port:'',
    option:'',
    param: [],
    statusOne:9,
    statusTwo:9,
    sum:0
};


var readHandler = function(info)
{


  if(scanning)
  {
    //console.log("Read!");
  	var tempresponseScanPacket = (new Uint8Array(info.data));
  	for(j = 0; j < tempresponseScanPacket.length; j++)
  	{

      //serialBuffer[serialIndex] = ;
  		serialBuffer.push(tempresponseScanPacket[j]);
  		serialIndex = serialIndex + 1;
  	}

    // console.log("Read1!---------------------------");
  	// for(j = 0; j < serialBuffer.length; j++)
  	// {
  	// 	console.log(serialBuffer[j]);
  	// }

  	while(serialIndex >= 0)
  	{

      if(responseScanPacketIndex == -1  )
      {
  				responseScanPacketIndex = 0;
  				responseScanPacket[responseScanPacketIndex] = serialBuffer.shift(); //put first element  from serialBuffer into response packet and shift the array
  				serialIndex = serialIndex - 1;
      }
  		else if(responseScanPacketIndex == 0  )
  		{
  				//console.log("SECOND BYTE PACKET!---------------------------");
  				responseScanPacketIndex = 1;
  				responseScanPacket[responseScanPacketIndex] = serialBuffer.shift(); //put first element  from serialBuffer into response packet and shift the array
  				serialIndex = serialIndex - 1;


  		}
  		else if(responseScanPacketIndex == 6  )
  		{
        if(serialBuffer[0] == 10)
        {

          //console.log('responsePacket');
          //console.log(responseScanPacket);
          //console.log('serialBuffer[0]');
          //console.log(serialBuffer[0]);
  			   responseScanPacketIndex = responseScanPacketIndex + 1;
  			   responseScanPacket[responseScanPacketIndex] = serialBuffer.shift();
  			   serialIndex = serialIndex - 1;

           //console.log('responsePacket');
           //console.log(responseScanPacket);

        }
        else
        {
          serialBuffer.shift();
          serialIndex = serialIndex - 1;
          //responseScanPacketIndex = -1;
          //console.log('error');
          //console.log(responseScanPacket);
        }

  		}
  		else if(responseScanPacketIndex > 7)
  		{
  				responseScanPacketIndex = -1;

  				//console.log("--------RESET? -------");
  		}
  		else
  		{
  //console.log("Readelseess1.5!");
  //console.log(responseScanPacketIndex);

  			responseScanPacketIndex = responseScanPacketIndex + 1;
  			responseScanPacket[responseScanPacketIndex] = serialBuffer.shift();
  			serialIndex = serialIndex - 1;
  		}

  		if(responseScanPacketIndex == 7)
  		{
  			packetsReceived = packetsReceived + 1;
  			//console.log("---SUCCESS----------");
  			/*
  			document.getElementById("packetsReceived").innerHTML = packetsReceived;

  		  	//console.log("---SUCCESS----------");

  			document.getElementById("resp0h").innerHTML = "0x" + responseScanPacket[0].toString(16).toUpperCase();
  			document.getElementById("resp1h").innerHTML = "0x" + responseScanPacket[1].toString(16).toUpperCase();
  			document.getElementById("resp2h").innerHTML = "0x" + responseScanPacket[2].toString(16).toUpperCase();
  			document.getElementById("resp3h").innerHTML = "0x" + responseScanPacket[3].toString(16).toUpperCase();
  			document.getElementById("resp4h").innerHTML = "0x" + responseScanPacket[4].toString(16).toUpperCase();
  			document.getElementById("resp5h").innerHTML = "0x" + responseScanPacket[5].toString(16).toUpperCase();
  			document.getElementById("resp6h").innerHTML = "0x" + responseScanPacket[6].toString(16).toUpperCase();
  			document.getElementById("resp0l").innerHTML = twoBytesTogether;
  			*/
  			//console.log("---ararSUCCESS----------");
  			/***** START SCANNABLE CODE ******/

  			var distance = (responseScanPacket[4] << 8) + (responseScanPacket[3]);

  			var angle_int = (responseScanPacket[2] << 8) + (responseScanPacket[1]);

  			var degree = 1.0 * ((angle_int >> 4) + ((angle_int & 15) / 16.0));

  			var signal_strength = responseScanPacket[5];

  			/***** END SCANNABLE CODE ******/

  			// var twoBytesTogether = (responseScanPacket[2] << 8) + (responseScanPacket[3]);
  			// var degree = (twoBytesTogether / 4095) * 360;
  				//console.log("Degree :" + degree);
  			if(degree <= 360 && degree >=0)
  			{
  				//document.getElementById("resp0lconverted").innerHTML = degree;
  				//console.log("ERRORR");
  				//console.log(degree);
  				//console.log(responseScanPacket[0] + " " +responseScanPacket[1] + " " +responseScanPacket[1] + " " +responseScanPacket[3] + " " +responseScanPacket[4] + " " +responseScanPacket[5] + " " +responseScanPacket[6] + " " );

  				//twoBytesTogether = (responseScanPacket[4] << 8) + (responseScanPacket[5]);
  				//document.getElementById("resp02").innerHTML = twoBytesTogether;

  				//var distance = twoBytesTogether;
  				var maxDistance = document.getElementById("maxDistance").value;


  				//find the smallest, canvas height or width, and use that as an upper bound
  				if(canvasHeight < canvasWidth)
  				{

  					distance = (distance / maxDistance) * (canvasHeight/2);
  				}
  				else
  				{

  					distance = (distance / maxDistance) * (canvasWidth/2);

  				}



  				//document.getElementById("resp02converted").innerHTML = distance;


  				responseScanPacketIndex = -1;
  				degree = 360 - degree;
  				drawPoint(degree,distance,signal_strength );

  				//console.log("Degree mapped:" + degree);
  			}

  			if(degree >lastDegree)
  			{
  				direction = 0;//0 is ccw
  			}
  			else
  			{
  				direction = 1;//1 is cw
  			}

  			if(document.getElementById('clearOnSweep').checked == true)
  			{
  				//if direction has changed, call draw() to clear canvas
  				if (direction != lastDirection)
  				{
  					draw();
  				}
  			}

  			lastDegree = degree;
  			lastDirection = direction;

		    }


	    }
    }
    else
    {
      serialBuffer = [];
      var tempresponsePacket = (new Uint8Array(info.data));
      for(j = 0; j < tempresponsePacket.length; j++)
      {

        //serialBuffer[serialIndex] = ;
        serialBuffer.push(tempresponsePacket[j]);
        serialIndex = serialIndex + 1;
      }

      // console.log("Read1!---------------------------");
      // for(j = 0; j < serialBuffer.length; j++)
      // {
      //   console.log(serialBuffer[j]);
      // }

      if(serialBuffer[0] == 0)
      {
        serialBuffer.shift();
        serialIndex = serialIndex - 1;
      }

      while(serialIndex >= 0)
      {

        switch(rxState)
        {
          case state.waitForCMD1:
            rp.port = serialBuffer.shift(); //put first element  from serialBuffer into response packet and shift the array
            serialIndex = serialIndex - 1;
            rxState = state.waitForCMD2;
            //console.log("port: ");
            //console.log(rp);
          break;
          case state.waitForCMD2:
            rp.option = serialBuffer.shift();
            serialIndex = serialIndex - 1;
            rxState = state.waitForParam;
            //console.log("option: ");
            //console.log(rp);
          break;
          case state.waitForParam:
            serialIndex = serialIndex - 1;
            if(serialBuffer[0] == 10)
            {
              serialBuffer.shift();
              rxState = state.waitForStatus1;
            }
            else
            {
              rp.param.push(serialBuffer.shift());
            }
            //console.log("option: ");
            //console.log(rp);
          break;
          case state.waitForStatus1:
            rp.statusOne = serialBuffer.shift();
            serialIndex = serialIndex - 1;
            rxState = state.waitForStatus2;
            //console.log("status1: ");
            //console.log(rp);
          break;
          case state.waitForStatus2:
            rp.statusTwo = serialBuffer.shift();
            serialIndex = serialIndex - 1;
            rxState = state.waitForSumByte;
            //console.log("status2: ");
            //console.log(rp);
          break;
          case state.waitForSumByte:
            //if sum is incorrect
            rp.sum = serialBuffer.shift();
            serialIndex = serialIndex - 1;

            tempSum = ((rp.statusOne + rp.statusTwo) & 0x3F) + 0x30;

            if(tempSum == rp.sum)
            {
              //if correct
              rxState = state.waitForTerm1;
            }
            else
            {
              rxState = state.waitForCMD1;
            }
            //console.log("sum: ");
            //console.log(rp);
          break;
          case state.waitForTerm1:
            if(serialBuffer[0] == 10)
            {
              rxState = state.waitForTerm2;
            }
            else
            {
              rxState = state.waitForCMD1;
            }
            serialBuffer.shift();
            serialIndex = serialIndex - 1;
            //console.log("term1: ");
            //console.log(rp);
          break;
          case state.waitForTerm2:
            if(serialBuffer[0] == 10)
            {
              //Evaluate full packet
              console.log("Packet....");
              console.log(rp);


              //Motor Speed response
              //document.getElementById("rotationSpeed").value = responseScanPacket[3];
              //document.getElementById("speedValue").value = responseScanPacket[3];

              //speedValue.value = responseScanPacket[3];
              //rotationSpeed.value = responseScanPacket[3];
            }
            rxState = state.waitForCMD1;
            c = serialBuffer.shift();
            serialIndex = serialIndex - 1;
            //console.log("term2: ");
            //console.log(rp);
          break;
        }
      }
    }

};
	var lastDegree;
	var direction;
	var lastDirection
	var canvas = document.getElementById('canvasRoom');
	var ctx;

	var r = 200.0;
	var centerX = document.getElementById('xcenterRange').value;
	var centerY  = document.getElementById('ycenterRange').value;
	var lastX = 750;
	var lastY = 750;


  var canvasHeight = window.innerHeight;
  var canvasWidth = window.innerWidth-240;

	var scanning = 0;

function drawPoint(degree, distance, signal_strength)
{
	//console.log("fill2");

	//console.log(document.getElementById('xcenterRange').value);
     	if (canvas.getContext)
     	{
        	//var ctx = canvas.getContext("2d");
			tempR =  distance ;
				//console.log(tempR);
				var x = tempR * Math.cos(toRadians(degree));
				var y = tempR * Math.sin(toRadians(degree));
				//console.log(x);
				//console.log(y);
			//ctx.fillStyle="#00FF00";
			if(document.getElementById("signalStrength").checked == true)
			{

				if(signal_strength >= 0 && signal_strength < 150)
				{
					ctx.fillStyle="#FF0009";
				}
				else if(signal_strength >= 150 && signal_strength < 160)
				{
					ctx.fillStyle="#FF9500";
				}
				else if(signal_strength >= 160 && signal_strength < 180)
				{
					ctx.fillStyle="#F7FF00";
				}
				else if(signal_strength >= 180 && signal_strength < 200)
				{
					ctx.fillStyle="#00B3FF";
				}
				else if(signal_strength >= 200 && signal_strength <= 255)
				{
					ctx.fillStyle="#09FF00";
				}
			}
			else
			{
				ctx.fillStyle="#FC0925";
			}

				ctx.fillRect (x+centerX, y+centerY, 3, 3);



			// 	ctx.fillRect (x, y2, 5, 5);


	//Line drawing only draw lines if requested
	if(document.getElementById("drawLines").checked == true)
	{
	  ctx.beginPath();
      ctx.moveTo(lastX, lastY);
      ctx.lineTo(x+centerX, y+centerY);
      ctx.stroke();
     }
      lastX = x+centerX;
      lastY = y+centerY;

	  //ctx.fillRect (0, 0, 50, 50);
      	}

}


function draw()
{

	if (canvas.getContext)
	{
		ctx = canvas.getContext("2d");

		ctx.fillStyle="#FFFFFF";
		ctx.fillRect(0,0,canvasWidth,canvasHeight);
		//ctx.fillRect(0,0,1000,500);

	  ctx.fillStyle="#000000";
	  ctx.fillRect(centerX-25, centerY-25, 10, 10);
	  //console.log(centerY);

	}
}

function toDegrees (angle)
{
	return angle * (180 / Math.PI);
}

function toRadians (angle) {
  return angle * (Math.PI / 180);
}

/*
document.addEventListener('DOMContentLoaded', function () {
  draw();

	//drawPoint(45, 200);
  //drawPoint(135, 200);
  //drawPoint(225, 350);
  //drawPoint(315, 500);

});
*/

window.onload =  function() {

	  redrawCanvas();
    centerY = canvasHeight/2;
    centerX = canvasWidth/2;
    document.getElementById("ycenterRange").value =centerY;
    document.getElementById("xcenterRange").value =centerX;

}
 window.onresize = function() {
   redrawCanvas();
}

function redrawCanvas()
{

    //canvas = document.getElementById("canvasRoom");
    canvasHeight = window.innerHeight;
    canvasWidth = window.innerWidth-240;

    //console.log(document.getElementById("setup").offsetHeight);
    canvas.width = canvasWidth;
    canvas.height = canvasHeight;

    centerY = canvasHeight/2;
    centerX = canvasWidth/2;
    document.getElementById("ycenterRange").max =canvasHeight;
    document.getElementById("xcenterRange").max =canvasWidth;

    //ctx = canvas.getContext("2d");

    draw();

}
