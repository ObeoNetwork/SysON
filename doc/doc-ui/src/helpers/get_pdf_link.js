'use strict'

module.exports = ({ data }) => {
  return '/' + data.root.page.component.name +
         '/' + data.root.page.version +
         '/' + data.root.page.component.title.toLowerCase().replaceAll(' ', '-') +
         '.pdf'
}
