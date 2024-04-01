<template>
  <div :id="name"></div>
</template>

<span class="text-default">18px</span>

<script>
export default {
  name: "CKEditor",
  props: {
    value: {
      type: String,
      default: ''
    },
    name: {
      type: String,
      default: 'editor'
    },
    height: {
      type: String,
      default: '600px'
    },
  },
  data() {
    return {
      editor: null
    }
  },
  mounted() {

  },
  watch: {
    name: {
      handler() {
        this.$nextTick(() => {
          this.init();
        })
      },
      immediate: true
    }
  },
  methods: {
    init() {
      const that = this;
      ClassicEditor.create( document.querySelector( '#' + this.name ), {
        ckfinder: {
          uploadUrl: '/ckfinder/connector?command=QuickUpload&type=Files&responseType=json',
        },
        toolbar: {
          shouldNotGroupWhenFull: true
        },
        fontFamily: {
          options: [
            'Arial'
          ]
        },
      }).then(editor => {
        editor.editing.view.change( writer => {
          writer.setStyle('min-height', that.height, editor.editing.view.document.getRoot());
          editor.setData(this.value)
        });
        editor.model.document.on('change:data', function (e) {
          that.$emit('input', editor.getData())
        });
        that.editor = editor;
      }).catch( error => {
        console.error( 'There was a problem initializing the editor.', error );
      });
    }
  },
  beforeDestroy() {
    if (this.editor) {
      this.editor.destroy();
    }
  }
}
</script>

<style scoped>

</style>
