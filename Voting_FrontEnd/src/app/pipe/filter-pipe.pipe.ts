import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter'
})
export class FilterPipePipe implements PipeTransform {

  transform(items: any[], searchTerm: string, keys: string[]): any[] {
    if (!items) return [];
    if (!searchTerm) return items;

    searchTerm = searchTerm.toLowerCase();
    console.log(items, searchTerm)
    return items.filter(item => {
      return keys.some(key => {
        return item[key]?.toString().toLowerCase().includes(searchTerm);
      });

    });
  }
}
