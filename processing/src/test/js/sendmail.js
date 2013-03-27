var nodemailer = require("nodemailer");
var loop = require('./nodeload/lib/loop');

// create reusable transport method (opens pool of SMTP connections)


// setup e-mail data with unicode symbols
var mailOptions = {
    from: "Fred Foo  <foo@blurdybloop.com>", // sender address
    to: "bar@blurdybloop.com, baz@blurdybloop.com", // list of receivers
    subject: "Hello!", // Subject line
    text: "Hello world " // plaintext body
};

var requests = 0,
l = new loop.MultiLoop({
    fun: function(finished) { 
    	// send mail with defined transport object
    	smtpTransport = nodemailer.createTransport("SMTP",{
			host: "127.0.0.1",
			port: 8025,
		    auth: {
		        user: "dark_lord",
		        pass: "neverguess"
		    }
		});
    	smtpTransport.sendMail(mailOptions, function(error, response){
    	    if(error){
    	        console.log(error);
    	    }else{
    	    	requests++;
    	        console.log("Message sent: " + response.message);
    	        console.log('Total requests: ' + requests); 
    	    }
			finished();
    	});
    },
    rps: 100000,
    duration: 30,
    concurrency: 1000
}).start();
l.on('end', function() { 
	smtpTransport.close();
	console.log('Total requests: ' + requests); 
});

