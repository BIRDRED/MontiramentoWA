google.load('visualization', '1', {packages: ['corechart', 'bar']});
google.setOnLoadCallback(drawStacked);
			function drawStacked() {
			      var data = new google.visualization.DataTable();
			      data.addColumn('timeofday', 'Time of Day' , 'red');
			      data.addColumn('number', 'Visits');

			      data.addRows([
                        #{vb.chartVisitorsACOM}
			       ]);

			      var options = {
			        title: 'Visitas ACOM',
			        colors: ['red'],
			        isStacked: true,
			        hAxis: {
			          title: 'Tempo',
			          format: 'h:mm a',
			          viewWindow: {
			            min: [0, -30, 0],
			            max: [24, 0, 0]
			          }
			        },
			        vAxis: {
			          title: 'Escala 1: 1000'
			        }
			      };

			      var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
			      chart.draw(data, options);
			    }