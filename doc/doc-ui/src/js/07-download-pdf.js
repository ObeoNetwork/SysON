;(function () {
  'use strict'

  var downloadButton = document.querySelector('#download')

  downloadButton.addEventListener('click', function () {
    var articleContent = document.querySelector('article')
    var title = document.querySelector('h1.page')

    var opt = {
      margin: 1,
      filename: title.textContent + '.pdf',
      image: { type: 'jpeg', quality: 0.98 },
      html2canvas: { scale: 2 },
      pagebreak: { mode: ['avoid-all', 'css', 'legacy'] },
    }

    window.html2pdf().set(opt).from(articleContent).save()
  })
})()
