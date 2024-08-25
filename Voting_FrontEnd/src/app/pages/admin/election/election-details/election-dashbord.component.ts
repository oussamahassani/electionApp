import { Component, OnInit, ElementRef } from '@angular/core';
import { DashbordService } from '../../../../services/dashbord.service';
import Chart from 'chart.js/auto'

@Component({
    selector: 'app-election-dashbord',
    templateUrl: './election-dashbord.component.html',
    styleUrls: ['./election-dashbord.component.css']
})
export class ElectionDashbordComponent implements OnInit {
    private electionByCity: any;
    private ctx: any;
    private chart: any;
    private electionBygender: any;
    private ctx1: any;
    private chart1: any;
    labelcoutUserByDate: any;
    datacoutUserByDate: any;
 
    constructor(
        private el: ElementRef,
        private dashbordService: DashbordService,
    ) { }

    ngOnInit(): void {
        this.dashbordService.getDashbordInfo().subscribe(res => {
        
            let datacoutUserByDate = res.countColisByDate.map((el: any) => "" + el[2]);
            let labelcoutUserByDate = res.countColisByDate.map((el: any) => el[0] + "-" + el[1]);
            this.datacoutUserByDate = datacoutUserByDate;
            this.labelcoutUserByDate = labelcoutUserByDate;
            this.dochart(labelcoutUserByDate, datacoutUserByDate)
            this.dochartByGender(labelcoutUserByDate, datacoutUserByDate)
        }
        )
    }
    dochart(label: any, data: any) {
        this.electionByCity = this.el.nativeElement.querySelector('#electionByCity');

        this.ctx = this.electionByCity.getContext('2d');

        this.chart = new Chart(this.ctx, {
            type: 'line',
            data: {
                labels: label,
                datasets: [{
                    label: 'Election by City',
                    data: data,

                    borderColor: [
                        'rgba(255, 99, 132, 1)',

                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {

                }
            }
        });
    }
    dochartByGender(label: any, data: any) {
        this.electionBygender = this.el.nativeElement.querySelector('#electionBygender');

        this.ctx1 = this.electionBygender.getContext('2d');

        this.chart1 = new Chart(this.ctx1, {
            type: 'bar',
            data: {
                labels: label,
                datasets: [{
                    label: 'Election By gender ',
                    data: data,

                    borderColor: [
                        'rgba(255, 99, 132, 1)',

                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {

                }
            }
        });

    }

}